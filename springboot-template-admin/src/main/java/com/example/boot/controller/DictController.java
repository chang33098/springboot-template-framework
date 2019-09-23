package com.example.boot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.model.dict.payload.CreateDictPLO;
import com.example.boot.model.dict.payload.FindDictTablePLO;
import com.example.boot.model.dict.payload.ModifyDictPLO;
import com.example.boot.model.dict.response.FindDictTableRO;
import com.example.boot.model.dict.response.ModifyDictRO;
import com.example.boot.response.ResponseBodyBean;
import com.example.boot.service.DictService;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDictOption;
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
import java.util.stream.Collectors;

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
    public String dict() {
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

    @GetMapping(value = "modify/{dict_id}")
    public String modifyDict(@PathVariable(value = "dict_id") Long dictId, Model model) {
        SystemDict dict = dictService.getDictById(dictId);
        List<SystemDictOption> options = dictService.getOptionListByDictId(dictId);

        ModifyDictRO dictRO = new ModifyDictRO();
        BeanUtil.copyProperties(dict, dictRO);
        List<ModifyDictRO.DictOption> optionROS = options.stream().map(option -> {
            ModifyDictRO.DictOption optionRO = new ModifyDictRO.DictOption();
            optionRO.setCode(option.getCode());
            optionRO.setValue(option.getValue());
            return optionRO;
        }).collect(Collectors.toList());
        dictRO.setOptions(optionROS);

        model.addAttribute("dict", dictRO);

        return "system/dict/dict_modify";
    }

    @PutMapping(value = "modify/{dict_id}")
    @ResponseBody
    public ResponseBodyBean modifyDict(@PathVariable(value = "dict_id") Long dictId,
                                       @RequestBody @Valid ModifyDictPLO plo) {
        dictService.modifyDict(dictId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{dict_id}")
    @ResponseBody
    public ResponseBodyBean deleteDict(@PathVariable(value = "dict_id") Long dictId) {
        dictService.deleteDict(dictId);
        return ResponseBodyBean.ofSuccess();
    }
}
