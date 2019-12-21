package com.example.boot.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleEntity;
import com.example.boot.springboottemplatebase.domain.systemuser.entity.SystemUserEntity;
import com.example.boot.springboottemplatebase.service.SystemRoleService;
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
 * @date 2019/11/17 22:22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@WebAppConfiguration
public class SystemUserEntityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SystemRoleService roleService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();  //初始化MockMvc对象
    }

    @Test
    public void listTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/system/user/list?pageNo={page-no}&pageSize={page-size}", 1, 10
        ).characterEncoding("UTF-8");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
    }

    @Test
    public void createTest() throws Exception {
        final List<SystemRoleEntity> randomRoleList = roleService.list();
        final List<Long> randomRoleIdList = randomRoleList.stream().map(SystemRoleEntity::getId).collect(Collectors.toList());

        final List<String> randomFolder1 = Stream.of("folder1-1", "folder1-2", "folder1-3").collect(Collectors.toList());
        final List<String> randomFolder2 = Stream.of("folder2-1", "folder2-2", "folder2-3", "folder2-4").collect(Collectors.toList());
        final List<String> randomAvatar = Stream.of("avatar1.jpg", "avatar2.jpg", "avatar3.jpg").collect(Collectors.toList());

        final String avatar = RandomUtil.randomEle(randomFolder1) + StrUtil.SLASH +
                RandomUtil.randomEle(randomFolder2) + StrUtil.SLASH +
                RandomUtil.randomEle(randomAvatar);

        SystemUserEntity payload = new SystemUserEntity();
        payload.setUsername("username-" + RandomUtil.randomString(8));
        payload.setPassword(RandomUtil.randomString(9));
        payload.setAvatar(avatar);
        payload.setNickname("create-user-nickname-" + RandomUtil.randomString(8));
        payload.setPhone(RandomUtil.randomNumbers(11));
        payload.setDescription("create-user-description-" + RandomUtil.randomString(50));
        payload.setRoleId(RandomUtil.randomEle(randomRoleIdList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/user/create")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(payload))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void modifyTest() throws Exception {
        final List<SystemRoleEntity> randomRoleList = roleService.list();
        final List<Long> randomRoleIdList = randomRoleList.stream().map(SystemRoleEntity::getId).collect(Collectors.toList());

        final List<String> randomFolder1 = Stream.of("folder1-1", "folder1-2", "folder1-3").collect(Collectors.toList());
        final List<String> randomFolder2 = Stream.of("folder2-1", "folder2-2", "folder2-3", "folder2-4").collect(Collectors.toList());
        final List<String> randomAvatar = Stream.of("modify-avatar1.jpg", "modify-avatar2.jpg", "modify-avatar3.jpg").collect(Collectors.toList());

        final String avatar = RandomUtil.randomEle(randomFolder1) + StrUtil.SLASH +
                RandomUtil.randomEle(randomFolder2) + StrUtil.SLASH +
                RandomUtil.randomEle(randomAvatar);

        SystemUserEntity payload = new SystemUserEntity();
        payload.setId(5L);
        payload.setPassword(RandomUtil.randomString(9));
        payload.setAvatar(avatar);
        payload.setNickname("modify-user-nickname-" + RandomUtil.randomString(8));
        payload.setPhone(RandomUtil.randomNumbers(11));
        payload.setDescription("modify-user-description-" + RandomUtil.randomString(50));
        payload.setRoleId(RandomUtil.randomEle(randomRoleIdList));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/user/modify")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(payload))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteTest() throws Exception {
        final Long dataId = 16L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/system/user/delete?data-id={dataId}", dataId)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }
}
