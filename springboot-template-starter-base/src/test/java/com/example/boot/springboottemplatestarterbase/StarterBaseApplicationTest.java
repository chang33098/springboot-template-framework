package com.example.boot.springboottemplatestarterbase;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * description: starter-base模块下application类
 *
 * @author chang_
 * @date 17/2/2020
 * @time 10:56 下午
 **/
@Slf4j
@MapperScan("com.example.boot.*.mapper")
@SpringBootApplication
public class StarterBaseApplicationTest {

    public static void main(String[] args) {
        SpringApplication.run(StarterBaseApplicationTest.class, args);
    }
}
