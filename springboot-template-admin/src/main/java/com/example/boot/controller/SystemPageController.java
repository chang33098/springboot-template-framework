package com.example.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.boot.springboottemplatebase.base.controller.BaseController;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPageEntity;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

/**
 * <p>系统模块-系统页面</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Slf4j
@Controller
@RequestMapping("system/page")
public class SystemPageController extends BaseController<SystemPageEntity, SystemPageService> {

    public SystemPageController() {
        super("/system/page", "system_page");
    }

    /**
     * 执行系统页面列表的分页查询功能
     *
     * @param pageNo   页码
     * @param pageSize 页数
     * @param payload  请求参数
     * @return 分页数据
     * @throws IllegalAccessException
     */
    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemPageEntity>> getPageList(@RequestParam(value = "page-no", defaultValue = "1") Integer pageNo,
                                                                 @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize,
                                                                 SystemPageEntity payload) throws IllegalAccessException {
        return ResponseBodyBean.ofSuccess();
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createPage(@RequestBody @Valid CreatePagePLO payload) {

        return ResponseBodyBean.ofSuccess();
    }

    @PutMapping(value = "modify")
    @ResponseBody
    public ResponseBodyBean modifyPage(@RequestBody @Valid ModifyPagePLO payload) {
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseBodyBean deletePage(@RequestParam(value = "data-id") Long dataId) {
        return ResponseBodyBean.ofSuccess();
    }
}
