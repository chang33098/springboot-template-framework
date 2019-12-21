package com.example.boot.controller;

import com.example.boot.springboottemplatebase.base.controller.BaseController;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systemdict.entity.SystemDictEntity;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.service.SystemDictService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>前端控制器</p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Slf4j
@Controller
@RequestMapping(value = "system/dict")
public class SystemDictController extends BaseController<SystemDictEntity, SystemDictService> {

    public SystemDictController() {
        super("/system/dict", "system_dict");
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean create(@RequestBody @Valid CreateDictPLO payload) {
        service.create(payload);
        return ResponseBodyBean.ofSuccess();
    }
}
