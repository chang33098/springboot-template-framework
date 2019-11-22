package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.response.dict.payload.CreateDictPLO;
import com.example.boot.response.dict.payload.ModifyDictPLO;
import com.example.boot.springboottemplatedomain.dict.persistent.SystemDict;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
public interface SystemDictService extends IService<SystemDict> {

    void createDict(CreateDictPLO plo);

    void modifyDict(ModifyDictPLO plo);

    void deleteDict(Long dictId);
}
