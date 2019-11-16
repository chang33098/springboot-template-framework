package com.example.boot.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.model.mybatistest.MybatisTablePLO;
import com.example.boot.model.mybatistest.MybatisTest1;
import com.example.boot.service.MybatisTest1Service;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/10 23:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MybatisControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  //初始化MockMvc对象
        log.info("开始测试-----------------");
    }

    @After
    public void after() {
        log.info("测试结束-----------------");
    }

    @Test
    public void listTest() throws Exception {
        log.info("************[execute listTest method]**************");

        MybatisTablePLO plo = new MybatisTablePLO();
        plo.setPageNo(1); //设置页码
        plo.setPageSize(10); //设置数量

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/mybatisTest1/list?pageNo={}&limit={}", plo.getPageNo(), plo.getPageSize()).contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void createTest() throws Exception {
        log.info("************[execute createTest method]**************");

        MybatisTest1 params = new MybatisTest1();
        params.setIntColumn(RandomUtil.randomInt(10000));
        params.setStringColumn("create-" + RandomUtil.randomString(18));
        params.setDoubleColumn(new BigDecimal(RandomUtil.randomDouble(10000)));
        params.setTimestampColumn(new Timestamp(System.currentTimeMillis()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mybatisTest1/create")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(params))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void modifyTest() throws Exception {
        log.info("************[execute modifyTest method]**************");

        MybatisTest1 params = new MybatisTest1();
        params.setId(1L);
        params.setIntColumn(RandomUtil.randomInt(10000));
        params.setStringColumn("modify-" + RandomUtil.randomString(18));
        params.setDoubleColumn(new BigDecimal(RandomUtil.randomDouble(10000)));
        params.setTimestampColumn(new Timestamp(System.currentTimeMillis()));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/mybatisTest1/modify")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(params))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteTest() throws Exception {
        log.info("************[execute deleteTest method]**************");

        final Long dataId = 1L; //删除的数据ID

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/mybatisTest1/delete?id={}", dataId).contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }
}
