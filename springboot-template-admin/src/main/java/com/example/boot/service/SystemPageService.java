package com.example.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.model.page.payload.CreatePagePLO;
import com.example.boot.model.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPageService extends IService<SystemPage> {

    void create(CreatePagePLO plo);

    void modify(ModifyPagePLO plo);

    void delete(Long pageId);
}
