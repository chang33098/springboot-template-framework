package com.example.boot.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.boot.springboottemplatebase.base.controller.BaseController;
import com.example.boot.springboottemplatebase.base.response.ResponseBodyBean;
import com.example.boot.springboottemplatebase.domain.systemdict.entity.SystemDictEntity;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
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

    @GetMapping(value = "list")
    @ResponseBody
    public ResponseBodyBean<IPage<SystemDictEntity>> dictList(@RequestParam(value = "page-no", defaultValue = "1") Integer pageNo,
                                                              @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize,
                                                              SystemDictEntity payload) throws IllegalAccessException {
//        IPage<SystemDictEntity> page = this.(pageNo, pageSize, payload);
        return ResponseBodyBean.ofSuccess();
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean creatDict(@RequestBody @Valid CreateDictPLO payload) {
        service.create(payload);
        return ResponseBodyBean.ofSuccess();
    }

    @PutMapping(value = "modifyDict")
    @ResponseBody
    public ResponseBodyBean modifyDict(@RequestBody @Valid ModifyDictPLO payload) {
        service.modify(payload);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseBodyBean deleteDict(@RequestParam(value = "data-id") Long dataId) {
        service.delete(dataId);
        return ResponseBodyBean.ofSuccess();
    }
}
