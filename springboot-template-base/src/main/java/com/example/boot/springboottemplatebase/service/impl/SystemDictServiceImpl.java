package com.example.boot.springboottemplatebase.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
import com.example.boot.springboottemplatebase.mapper.SystemDictMapper;
import com.example.boot.springboottemplatebase.mapper.SystemDictOptionMapper;
import com.example.boot.springboottemplatebase.service.SystemDictOptionService;
import com.example.boot.springboottemplatebase.service.SystemDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemdict.persistent.SystemDict;
import com.example.boot.springboottemplatebase.domain.systemdict.persistent.SystemDictOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chang_
 * @since 2019-11-13
 */
@Service
@Transactional
public class SystemDictServiceImpl extends ServiceImpl<SystemDictMapper, SystemDict> implements SystemDictService {

    private final SystemDictOptionService dictOptionService;
    private final SystemDictOptionMapper dictOptionMapper;

    @Autowired
    public SystemDictServiceImpl(SystemDictOptionService dictOptionService, SystemDictOptionMapper dictOptionMapper) {
        this.dictOptionService = dictOptionService;
        this.dictOptionMapper = dictOptionMapper;
    }

    @Override
    public void createDict(CreateDictPLO dictPLO) {
        int uniqueName = this.count(new QueryWrapper<SystemDict>().lambda().eq(SystemDict::getName, dictPLO.getName()));
        Assert.isTrue(uniqueName < 1, "字典名称[{}]已存在，请不要重复添加", dictPLO.getName());
        int uniqueCode = this.count(new QueryWrapper<SystemDict>().lambda().eq(SystemDict::getDictCode, dictPLO.getDictCode()));
        Assert.isTrue(uniqueCode < 1, "字典代码[{}]已存在，请不要重复添加", dictPLO.getDictCode());

        SystemDict dict = new SystemDict();
        BeanUtil.copyProperties(dictPLO, dict);
        this.save(dict);

        List<SystemDictOption> dictOptions = dictPLO.getOptions().stream().map(option -> {
            SystemDictOption dictOption = new SystemDictOption();
            BeanUtil.copyProperties(option, dictOption);
            dictOption.setDictId(dict.getId());
            return dictOption;
        }).collect(Collectors.toList());
        dictOptionService.saveBatch(dictOptions);
    }

    @Override
    public void modifyDict(ModifyDictPLO dictPLO) {
        int uniqueName = this.count(new QueryWrapper<SystemDict>().lambda().notIn(SystemDict::getId, dictPLO.getDictId()).eq(SystemDict::getName, dictPLO.getName()));
        Assert.isTrue(uniqueName < 1, "字典名称[{}]已存在，请不要重复添加", dictPLO.getName());

        SystemDict dict = this.getById(dictPLO.getDictId()); //获取根据ID获取数据字典
        Assert.notNull(dict, "不存在ID[{}]的数据", dictPLO.getDictId());
        BeanUtil.copyProperties(dictPLO, dict);

        dictOptionMapper.deleteAllByDictId(dictPLO.getDictId()); //删除数据字典项(硬删除)

        List<SystemDictOption> dictOptions = dictPLO.getOptions().stream().map(option -> {
            SystemDictOption dictOption = new SystemDictOption();
            BeanUtil.copyProperties(option, dictOption);
            dictOption.setDictId(dict.getId());
            return dictOption;
        }).collect(Collectors.toList());
        dictOptionService.saveBatch(dictOptions);

        this.saveOrUpdate(dict);
    }

    @Override
    public void deleteDict(Long dictId) {
        dictOptionService.remove(new UpdateWrapper<SystemDictOption>().lambda().
                eq(SystemDictOption::getDictId, dictId)); //采用逻辑删除的方式
        this.removeById(dictId);
    }
}
