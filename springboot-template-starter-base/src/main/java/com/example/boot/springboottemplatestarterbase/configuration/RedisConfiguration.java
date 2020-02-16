package com.example.boot.springboottemplatestarterbase.configuration;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * description: Redis配置文件
 * <p>采用默认配置RedisAutoConfiguration.class</p>
 * <p>参考项目: https://github.com/johnnymillergh/jm-spring-boot-template</p>
 *
 * @author chang_
 * @date 16/2/2020
 * @time 4:48 下午
 **/
@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfiguration extends CachingConfigurerSupport {

    /**
     * Redis template. Support for &lt;String, Serializable&gt;
     */
    @Bean
    public RedisTemplate<String, Serializable> redisFactory(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(lettuceConnectionFactory);
        return template;
    }
}
