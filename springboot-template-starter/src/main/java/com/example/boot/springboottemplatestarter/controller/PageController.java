package com.example.boot.springboottemplatestarter.controller;

import cn.hutool.core.lang.Assert;
import com.example.boot.springboottemplatedomain.page.payload.CreatePagePLO;
import com.example.boot.springboottemplatedomain.page.payload.FindAllPagePLO;
import com.example.boot.springboottemplatedomain.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.page.response.ModifyPageRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/23 22:32
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/page")
public class PageController {

    private final PageService pageService;

    @Autowired
    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public String page() {
        return "system/page/syspage_list";
    }

    @GetMapping(value = "list")
    @ResponseBody
    public Page<SystemPage> findAllPage(FindAllPagePLO plo) {

        Page<SystemPage> pages = pageService.findAllPage(plo);
        return pages;
    }

    @GetMapping(value = "create")
    public String createPage() {
        return "system/page/syspage_create";
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createPage(@ModelAttribute CreatePagePLO plo) {
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{page_id}")
    public String modifyPage(@PathVariable(value = "page_id") Long pageId, Model model) {
        SystemPage page = pageService.getPageById(pageId);
        Assert.notNull(page, "无效的页面ID");

        ModifyPageRO pageRO = ModifyPageRO.transferPageRO(page);
        model.addAttribute("page", pageRO);

        return "system/page/syspage_modify";
    }

    @PutMapping(value = "modify/{page_id}")
    @ResponseBody
    public ResponseBodyBean modifyPage(@PathVariable(value = "page_id") Long pageId, @RequestBody ModifyPagePLO plo) {
        pageService.modifyPage(pageId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{page_id}")
    @ResponseBody
    public ResponseBodyBean deletePage(@PathVariable(value = "page_id") Long pageId) {
        pageService.deletePage(pageId);
        return ResponseBodyBean.ofSuccess();
    }
}
