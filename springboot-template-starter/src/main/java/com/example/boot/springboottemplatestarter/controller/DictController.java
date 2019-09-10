package com.example.boot.springboottemplatestarter.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.dict.payload.CreateDictPLO;
import com.example.boot.springboottemplatedomain.dict.payload.FindDictTablePLO;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.response.FindDictTableRO;
import com.example.boot.springboottemplatedomain.dict.response.GetParentDictListRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 0:06
 */
@Slf4j
@Controller
@RequestMapping(value = "system/dict")
public class DictController {

    private final DictService dictService;

    @Autowired
    public DictController(DictService dictService) {
        this.dictService = dictService;
    }

    @GetMapping
    public String dict(Model model) {
        List<GetParentDictListRO> dictROS = dictService.getParentDictList();
        model.addAttribute("dictTypes", dictROS);

        return "system/dict/dict_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindDictTableRO>> findDictTable(FindDictTablePLO plo) {
        Page<SystemDict> dictPage = dictService.findDictTable(plo);

        List<FindDictTableRO> dictROS = new ArrayList<>();
        dictPage.getContent().forEach(dict -> {
            FindDictTableRO dictRO = new FindDictTableRO();
            BeanUtil.copyProperties(dict, dictRO);
            dictROS.add(dictRO);
        });
        Page<FindDictTableRO> dictROPage = new PageImpl<>(dictROS, dictPage.getPageable(), dictPage.getTotalElements());

        return ResponseBodyBean.ofSuccess(dictROPage);
    }

    @GetMapping(value = "create")
    public String createDict() {
        return "system/dict/dict_create";
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createDict(@RequestBody @Valid CreateDictPLO plo) {
        dictService.createDict(plo);
        return ResponseBodyBean.ofSuccess();
    }
}
