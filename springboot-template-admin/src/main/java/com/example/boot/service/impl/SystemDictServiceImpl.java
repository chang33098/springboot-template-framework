package com.example.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.boot.mapper.SystemDictMapper;
import com.example.boot.model.dict.payload.CreateDictPLO;
import com.example.boot.model.dict.payload.ModifyDictPLO;
import com.example.boot.service.SystemDictOptionService;
import com.example.boot.service.SystemDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDictOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Service
@Transactional
public class SystemDictServiceImpl extends ServiceImpl<SystemDictMapper, SystemDict> implements SystemDictService {

    private final SystemDictOptionService dictOptionService;

    @Autowired
    public SystemDictServiceImpl(SystemDictOptionService dictOptionService) {
        this.dictOptionService = dictOptionService;
    }

    @Override
    public void createDict(CreateDictPLO plo) {
        int uniqueName = this.count(new QueryWrapper<SystemDict>().lambda().eq(SystemDict::getName, plo.getName()));
        Assert.isTrue(uniqueName < 1, "字典名称[{}]已存在，请不要重复添加", plo.getName());

        int uniqueCode = this.count(new QueryWrapper<SystemDict>().lambda().eq(SystemDict::getDictCode, plo.getDictCode()));
        Assert.isTrue(uniqueCode < 1, "字典代码[{}]已存在，请不要重复添加", plo.getDictCode());

        SystemDict dict = new SystemDict();
        BeanUtil.copyProperties(plo, dict);
        this.save(dict);

        List<SystemDictOption> dictOptions = plo.getOptions().stream().map(option -> {
            SystemDictOption dictOption = new SystemDictOption();
            BeanUtil.copyProperties(option, dictOption);
            dictOption.setDictId(dict.getId());
            return dictOption;
        }).collect(Collectors.toList());
        dictOptionService.saveBatch(dictOptions);
    }

    @Override
    public void modifyDict(ModifyDictPLO plo) {
        int uniqueName = this.count(new QueryWrapper<SystemDict>().lambda().notIn(SystemDict::getId, plo.getDictId()).eq(SystemDict::getName, plo.getName()));
        Assert.isTrue(uniqueName < 1, "字典名称[{}]已存在，请不要重复添加", plo.getName());

        SystemDict dict = this.getById(plo.getDictId()); //获取根据ID获取数据字典
        Assert.notNull(dict, "不存在ID[{}]的数据", plo.getDictId());
        BeanUtil.copyProperties(plo, dict);

        dictOptionService.remove(new UpdateWrapper<SystemDictOption>().lambda().eq(SystemDictOption::getDictId, plo.getDictId())); //删除数据字典项

        List<SystemDictOption> dictOptions = plo.getOptions().stream().map(option -> {
            SystemDictOption dictOption = new SystemDictOption();
            BeanUtil.copyProperties(option, dictOption);
            dictOption.setDictId(dict.getId());
            return dictOption;
        }).collect(Collectors.toList());
        dictOptionService.saveBatch(dictOptions);

        this.save(dict);
    }

    @Override
    public void deleteDict(Long dictId) {
        dictOptionService.remove(new UpdateWrapper<SystemDictOption>().lambda().eq(SystemDictOption::getDictId, dictId)); //删除数据字典项
        this.removeById(dictId);
    }
}
