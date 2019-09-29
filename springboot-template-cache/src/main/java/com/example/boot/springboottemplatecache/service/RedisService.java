package com.example.boot.springboottemplatecache.service;

import java.util.concurrent.TimeUnit;

/**
 * Created by EDZ on 2019/9/25.
 */
public interface RedisService {

    void putAndSetExpired(String key, String value, Long expireTime, TimeUnit unit);
}
