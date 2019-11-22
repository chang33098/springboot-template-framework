package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.persistent.SystemDict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
public interface SystemDictService extends IService<SystemDict> {

    void createDict(CreateDictPLO dictPLO);

    void modifyDict(ModifyDictPLO dictPLO);

    void deleteDict(Long dictId);
}
