package com.example.boot.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.ModifyPermissionPLO;
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

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/11/17 1:24
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@WebAppConfiguration
public class SystemPermissionEntityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  //初始化MockMvc对象
    }

    @Test
    public void listTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/system/permission/list?pageNo={page-no}&pageSize={page-size}", 1, 10
        ).characterEncoding("UTF-8");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void create() throws Exception {
        CreatePermissionPLO permissionPLO = new CreatePermissionPLO();
        permissionPLO.setPermissionCode("CREATE_" + RandomUtil.randomStringUpper(5));
        permissionPLO.setPermissionName("create-" + RandomUtil.randomString(8));
        permissionPLO.setDescription(RandomUtil.randomStringUpper(16));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/permission/create")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(permissionPLO))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void modify() throws Exception {
        ModifyPermissionPLO permissionPLO = new ModifyPermissionPLO();
        permissionPLO.setPermissionId(1L);
        permissionPLO.setPermissionCode("MODIFY_" + RandomUtil.randomStringUpper(5));
        permissionPLO.setPermissionName("modify-" + RandomUtil.randomString(8));
        permissionPLO.setDescription(RandomUtil.randomStringUpper(16));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/permission/modify")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(permissionPLO))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void delete() throws Exception {
        final Long dataId = 1L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/system/permission/delete?data-id={dataId}", dataId)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }
}
