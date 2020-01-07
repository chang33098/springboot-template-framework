package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPageEntity;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPageService extends IService<SystemPageEntity> {

    /**
     * 创建系统页面
     *
     * @param payload 参数载体
     */
    void create(CreatePagePLO payload);

    /**
     * 编辑系统页面
     *
     * @param payload 参数载体
     */
    void modify(ModifyPagePLO payload);

    /**
     * 删除系统页面
     *
     * @param pageId
     */
    void delete(Long pageId);
}
