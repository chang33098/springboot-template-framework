package com.example.boot.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/16 17:13
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@WebAppConfiguration
public class SystemPageEntityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  //初始化MockMvc对象
    }

    @Test
    public void tableTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/system/systempage/table?pageNo={pageNo}&pageSize={pageSize}",
                1, 10
        ).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void createTest() throws Exception {
        CreatePagePLO plo = new CreatePagePLO();
        plo.setPageCode("CREATE_" + RandomUtil.randomStringUpper(8));
        plo.setPageName(RandomUtil.randomString(16));
        plo.setPageUrl("/system/test/" + RandomUtil.randomString(5));
        plo.setDescription(RandomUtil.randomStringUpper(32));

        CreatePagePLO.PagePermission pagePermission1 = new CreatePagePLO.PagePermission();
        pagePermission1.setInterceptUrls("/test/create-interceptUrl1/" + RandomUtil.randomString(5)).setPermissionId(1L);
        CreatePagePLO.PagePermission pagePermission2 = new CreatePagePLO.PagePermission();
        pagePermission2.setInterceptUrls("/test/create-interceptUrl2/" + RandomUtil.randomString(5)).setPermissionId(2L);
        CreatePagePLO.PagePermission pagePermission3 = new CreatePagePLO.PagePermission();
        pagePermission3.setInterceptUrls("/test/create-interceptUrl3/" + RandomUtil.randomString(5)).setPermissionId(3L);
        CreatePagePLO.PagePermission pagePermission4 = new CreatePagePLO.PagePermission();
        pagePermission4.setInterceptUrls("/test/create-interceptUrl4/" + RandomUtil.randomString(5)).setPermissionId(4L);

        List<CreatePagePLO.PagePermission> pagePermissions = Stream.of(pagePermission1, pagePermission2, pagePermission3, pagePermission4).collect(Collectors.toList());
        plo.setPagePermissions(pagePermissions);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/systempage/create")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(plo))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void modifyTest() throws Exception {
        ModifyPagePLO plo = new ModifyPagePLO();
        plo.setPageId(13L); //页面ID
        plo.setPageCode("MODIFY_" + RandomUtil.randomStringUpper(8));
        plo.setPageName(RandomUtil.randomString(16));
        plo.setPageUrl("/system/test/" + RandomUtil.randomString(5));
        plo.setDescription(RandomUtil.randomStringUpper(32));

        ModifyPagePLO.PagePermission pagePermission1 = new ModifyPagePLO.PagePermission();
        pagePermission1.setInterceptUrls("/test/modify-interceptUrl1/" + RandomUtil.randomString(5)).setPermissionId(1L);
        ModifyPagePLO.PagePermission pagePermission2 = new ModifyPagePLO.PagePermission();
        pagePermission2.setInterceptUrls("/test/modify-interceptUrl1/" + RandomUtil.randomString(5)).setPermissionId(1L);

        List<ModifyPagePLO.PagePermission> pagePermissions = Stream.of(pagePermission1, pagePermission2).collect(Collectors.toList());
        plo.setPagePermissions(pagePermissions);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/system/systempage/modify")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(plo))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteTest() throws Exception {
        final Long pageId = 1L;
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/system/systempage/delete?page_id={pageId}", pageId)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }
}
