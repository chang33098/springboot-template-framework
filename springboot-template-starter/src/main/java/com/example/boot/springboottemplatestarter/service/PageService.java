package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.page.payload.CreatePagePLO;
import com.example.boot.springboottemplatedomain.page.payload.FindAllPagePLO;
import com.example.boot.springboottemplatedomain.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import org.springframework.data.domain.Page;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:04
 */
public interface PageService {

    /**
     * 查询系统页面
     *
     * @param plo 查询参数PLO
     * @return 系统页面
     */
    Page<SystemPage> findAllPage(FindAllPagePLO plo);

    /**
     * 通过页面ID获取系统页面
     *
     * @param pageId 页面ID
     * @return 系统页面
     */
    SystemPage getPageById(Long pageId);

    /**
     * 创建页面
     *
     * @param plo
     */
    void createPage(CreatePagePLO plo);

    /**
     * 编辑页面
     *
     * @param pageId
     * @param plo
     */
    void modifyPage(Long pageId, ModifyPagePLO plo);

    /**
     * 删除页面
     *
     * @param pageId
     */
    void deletePage(Long pageId);
}
