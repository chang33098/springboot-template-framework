package com.example.boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 22:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@WebAppConfiguration
public class SystemRoleControllerTest {


}
