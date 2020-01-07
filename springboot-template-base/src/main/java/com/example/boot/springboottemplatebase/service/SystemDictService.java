package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemdict.entity.SystemDictEntity;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.CreateDictPLO;
import com.example.boot.springboottemplatebase.domain.systemdict.payload.ModifyDictPLO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chang_
 * @since 2019-11-13
 */
public interface SystemDictService extends IService<SystemDictEntity> {

    /**
     * 创建数据字段
     *
     * @param payload 参数载体
     */
    void create(CreateDictPLO payload);

    /**
     * 编辑数据字典
     *
     * @param payload 参数载体
     */
    void modify(ModifyDictPLO payload);

    /**
     * 删除数据字典
     *
     * @param dictId 字典ID
     */
    void delete(Long dictId);
}
