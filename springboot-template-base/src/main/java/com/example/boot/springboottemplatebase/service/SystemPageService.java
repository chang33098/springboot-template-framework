package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempage.payload.CreatePagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.payload.ModifyPagePLO;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPage;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPageService extends IService<SystemPage> {

    void create(CreatePagePLO pagePLO);

    void modify(ModifyPagePLO pagePLO);

    void delete(Long pageId);

    String getPageCodeById(Long pageId);
}
