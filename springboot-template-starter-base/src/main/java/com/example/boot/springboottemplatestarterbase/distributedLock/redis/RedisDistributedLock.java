package com.example.boot.springboottemplatestarterbase.distributedLock.redis;

import cn.hutool.core.util.RandomUtil;
import com.example.boot.springboottemplatestarterbase.distributedLock.AbstractDistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description: Redis分布式锁
 * <p>采用Redis实现分布式锁</p>
 * <p>
 * NX: 表示只有当锁定资源不存在的时候才能 SET 成功。利用 Redis 的原子性，保证了只有第一个请求的线程才能获得锁，而之后的所有线程在锁定资源被释放之前都不能获得锁。
 * PX: expire 表示锁定的资源的自动过期时间，单位是毫秒。具体过期时间根据实际场景而定
 *
 * @author chang_
 * @date 16/2/2020
 * @time 5:52 下午
 **/
@Slf4j
@Service
public class RedisDistributedLock extends AbstractDistributedLock {

    private RedisTemplate<Object, Object> redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    private static final String UNLOCK_LUA;

    static {
        //释放Redis锁的LUA脚本
        //
        StringBuilder builder = new StringBuilder();
        builder.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        builder.append("then ");
        builder.append("    return redis.call(\"del\",KEYS[1]) ");
        builder.append("else ");
        builder.append("    return 0 ");
        builder.append("end ");
        UNLOCK_LUA = builder.toString();
    }

    public RedisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        super();
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
        boolean result = setRedis(key, expire);
        //如果获取锁失败，按照传入的重试次数进行重试
        while ((!result) && retryTimes-- > 0) {
            try {
                log.debug("lock failed, retrying..." + retryTimes);
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                return false;
            }
            result = setRedis(key, expire);
        }
        return result;
    }

    @Override
    public boolean releaseLock(String key) {
        //释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
        try {
            List<String> keys = Stream.of(key).collect(Collectors.toList());
            List<String> args = Stream.of(lockFlag.get()).collect(Collectors.toList());

            //使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
            //spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本

            Long result = redisTemplate.execute((RedisCallback<Long>) connection -> {
                Object nativeConnection = connection.getNativeConnection();
                //集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                //集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                //单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            });

            return result != null && result > 0;
        } catch (Exception e) {
            log.error("release lock occured an exception", e);
        }
        return false;
    }

    /**
     * Redis分布式锁-执行设置锁的脚本
     *
     * @param key    锁定的key(通常为ID或者是编号等唯一值)
     * @param expire 过期时间(单位毫秒)
     * @return 是否设置成功
     */
    private boolean setRedis(String key, long expire) {
        try {
            //实例化RedisCallback对象，执行Redis脚本方法
            //
            String result = redisTemplate.execute((RedisCallback<String>) connection -> {
                //将随机数存储在ThreadLocal对象中，释放锁的时候对随机数进行校验，判断两个值是否一致
                //
                String randomID = RandomUtil.randomString(16);
                lockFlag.set(randomID);

                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                return commands.set(key, randomID, "NX", "PX", expire);
            });
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            log.error("set redis occured an exception", e);
        }
        return false;
    }
}
