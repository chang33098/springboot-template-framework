package com.example.boot.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
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
 * @date 2019/11/13 23:14
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
@WebAppConfiguration
public class SystemDictControllerTest {

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
                "/system/dict/table?pageNo={pageNo}&pageSize={pageSize}", 1, 10
        ).characterEncoding("UTF-8").contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void createTest() throws Exception {
        CreateDictPLO dictPLO = new CreateDictPLO();
        dictPLO.setName("create-dict-name-" + RandomUtil.randomString(8));
        dictPLO.setDictCode("dict-code-" + RandomUtil.randomString(8));
        dictPLO.setDescription("description-" + RandomUtil.randomString(50));

        CreateDictPLO.DictOption option1 = new CreateDictPLO.DictOption();
        option1.setCode(RandomUtil.randomInt(2)).setValue("create-" + RandomUtil.randomStringUpper(8));
        CreateDictPLO.DictOption option2 = new CreateDictPLO.DictOption();
        option2.setCode(RandomUtil.randomInt(2)).setValue("create-" + RandomUtil.randomStringUpper(8));
        CreateDictPLO.DictOption option3 = new CreateDictPLO.DictOption();
        option3.setCode(RandomUtil.randomInt(2)).setValue("create-" + RandomUtil.randomStringUpper(8));

        List<CreateDictPLO.DictOption> dictOptions = Stream.of(option1, option2, option3).collect(Collectors.toList());
        dictPLO.setOptions(dictOptions);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/system/dict/create")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(dictPLO))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void modifyTest() throws Exception {
        ModifyDictPLO dictPLO = new ModifyDictPLO();
        dictPLO.setDictId(10L);
        dictPLO.setDictName("modify-dict-name-" + RandomUtil.randomString(8));
        dictPLO.setDescription("description-" + RandomUtil.randomString(50));

        ModifyDictPLO.DictOption option1 = new ModifyDictPLO.DictOption();
        option1.setOptionCode(String.valueOf(RandomUtil.randomInt(2))).setOptionValue("modify-" + RandomUtil.randomStringUpper(8));
        ModifyDictPLO.DictOption option2 = new ModifyDictPLO.DictOption();
        option2.setOptionCode(String.valueOf(RandomUtil.randomInt(2))).setOptionValue("modify-" + RandomUtil.randomStringUpper(8));

        List<ModifyDictPLO.DictOption> dictOptions = Stream.of(option1, option2).collect(Collectors.toList());
        dictPLO.setOptions(dictOptions);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/system/dict/modify")
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(JSONUtil.toJsonStr(dictPLO))
                .contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        log.info("************[http status  :   {}]**************", response.getStatus());
        log.info("************[response body:   {}]**************", result.getResponse().getContentAsString());
        log.info(result.getResponse().getContentAsString());
    }

    @Test
    public void deleteTest() throws Exception {
        final Long dictId = 16L;

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/system/dict/delete?dict_id={dictId}", dictId)
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
