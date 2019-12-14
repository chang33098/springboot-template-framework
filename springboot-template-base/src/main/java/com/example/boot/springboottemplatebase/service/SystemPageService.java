package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPageEntity;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPageService extends IService<SystemPageEntity> {

    void create(CreatePagePLO payload);

    void modify(ModifyPagePLO payload);

    void delete(Long pageId);
}
