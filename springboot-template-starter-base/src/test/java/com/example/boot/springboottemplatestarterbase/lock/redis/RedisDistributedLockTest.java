package com.example.boot.springboottemplatestarterbase.lock.redis;

import com.example.boot.springboottemplatestarterbase.StarterBaseApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
//@Transactional
@TestPropertySource(locations = "classpath:application.yml")
@SpringBootTest(classes = StarterBaseApplication.class)
public class RedisDistributedLockTest {

    @Autowired
    private RedisDistributedLock redisDistributedLock;

    @Before
    public void setUp() throws Exception {
        log.debug("启动{}，执行测试案例。", this.getClass().getName());
    }

    @After
    public void tearDown() throws Exception {
        log.debug("关闭{}，测试完毕。", this.getClass().getName());
    }

    @Test
    public void lock() {
        log.debug("执行Redis锁");
    }

    @Test
    public void releaseLock() {
        log.debug("释放Redis锁");
    }
}