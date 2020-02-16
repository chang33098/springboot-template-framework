package com.example.boot.springboottemplatestarterbase.distributedLock;

/**
 * description: 分布式锁组件接口
 * <p>定了了分布式锁的方法以及锁默认的超时时间</p>
 * <p>参考文章: https://my.oschina.net/dengfuwei/blog/1600681</p>
 *
 * @author chang_
 * @date 16/2/2020
 * @time 5:46 下午
 **/
@SuppressWarnings("unused")
public interface DistributedLock {

    long TIMEOUT_MILLIS = 30000;

    int RETRY_TIMES = Integer.MAX_VALUE;

    long SLEEP_MILLIS = 500;

    boolean lock(String key);

    boolean lock(String key, int retryTimes);

    boolean lock(String key, int retryTimes, long sleepMillis);

    boolean lock(String key, long expire);

    boolean lock(String key, long expire, int retryTimes);

    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    boolean releaseLock(String key);
}
