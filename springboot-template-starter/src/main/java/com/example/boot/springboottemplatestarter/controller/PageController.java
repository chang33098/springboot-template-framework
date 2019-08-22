package com.example.boot.springboottemplatestarter.controller;

import com.example.boot.springboottemplatedomain.page.payload.CreatePagePLO;
import com.example.boot.springboottemplatedomain.page.payload.FindPageTablePLO;
import com.example.boot.springboottemplatedomain.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.page.response.FindPageTableRO;
import com.example.boot.springboottemplatedomain.page.response.ModifyPageRO;
import com.example.boot.springboottemplatedomain.page.response.GetPageRO;
import com.example.boot.springboottemplatedomain.page.response.GetPagePermissionListRO;
import com.example.boot.springboottemplatestarter.response.ResponseBodyBean;
import com.example.boot.springboottemplatestarter.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
        return "system/page/page_list";
    }

    @GetMapping(value = "table")
    @ResponseBody
    public ResponseBodyBean<Page<FindPageTableRO>> findPageTable(FindPageTablePLO plo) {
        Page<SystemPage> POPAGE = pageService.findPageTable(plo);

        List<FindPageTableRO> pageROS = FindPageTableRO.create(POPAGE.getContent());
        Page<FindPageTableRO> ROPAGE = new PageImpl<>(pageROS, POPAGE.getPageable(), POPAGE.getTotalElements());

        return ResponseBodyBean.ofSuccess(ROPAGE);
    }

    @GetMapping(value = "get/{page_id}")
    public String getPage(@PathVariable(value = "page_id") Long pageId, Model model) {
        SystemPage page = pageService.getPageById(pageId);
        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListById(pageId);

        GetPageRO pageRO = GetPageRO.create(page, permissionRefs);
        model.addAttribute("page", pageRO);

        return "system/page/page_detail";
    }

    @GetMapping(value = "create")
    public String createPage() {
        return "system/page/page_create";
    }

    @PostMapping(value = "create")
    @ResponseBody
    public ResponseBodyBean createPage(@RequestBody @Valid CreatePagePLO plo) {
        pageService.createPage(plo);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "modify/{page_id}")
    public String modifyPage(@PathVariable(value = "page_id") Long pageId, Model model) {
        SystemPage page = pageService.getPageById(pageId);
        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListById(pageId);

        ModifyPageRO pageRO = ModifyPageRO.create(page, permissionRefs);
        model.addAttribute("page", pageRO);

        return "system/page/page_modify";
    }

    @PutMapping(value = "modify/{page_id}")
    @ResponseBody
    public ResponseBodyBean modifyPage(@PathVariable(value = "page_id") Long pageId,
                                       @RequestBody @Valid ModifyPagePLO plo) {
        pageService.modifyPage(pageId, plo);
        return ResponseBodyBean.ofSuccess();
    }

    @DeleteMapping(value = "delete/{page_id}")
    @ResponseBody
    public ResponseBodyBean deletePage(@PathVariable(value = "page_id") Long pageId) {
        pageService.deletePage(pageId);
        return ResponseBodyBean.ofSuccess();
    }

    @GetMapping(value = "choose_page")
    public String choosePage() {
        return "system/page/choose_page";
    }

    @GetMapping(value = "{page_id}/get_page_permission_list")
    @ResponseBody
    public ResponseBodyBean<List<GetPagePermissionListRO>> getPagePermissionList(@PathVariable(value = "page_id") Long pageId) {
        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListById(pageId);
        List<GetPagePermissionListRO> permissionROS = GetPagePermissionListRO.create(permissionRefs);

        return ResponseBodyBean.ofSuccess(permissionROS);
    }
}
