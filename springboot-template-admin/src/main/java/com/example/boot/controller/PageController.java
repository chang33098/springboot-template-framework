package com.example.boot.controller;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.model.page.payload.CreatePagePLO;
import com.example.boot.model.page.payload.FindPageTablePLO;
import com.example.boot.model.page.payload.ModifyPagePLO;
import com.example.boot.model.page.response.FindPageTableRO;
import com.example.boot.model.page.response.GetPagePermissionListRO;
import com.example.boot.model.page.response.GetPageRO;
import com.example.boot.model.page.response.ModifyPageRO;
import com.example.boot.model.ResponseBodyBean;
import com.example.boot.service.PageService;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<FindPageTableRO> pageROS = new ArrayList<>(POPAGE.getContent().size());
        POPAGE.getContent().forEach(page -> {
            FindPageTableRO pageRO = new FindPageTableRO();
            BeanUtil.copyProperties(page, pageRO);
            pageROS.add(pageRO);
        });
        Page<FindPageTableRO> ROPAGE = new PageImpl<>(pageROS, POPAGE.getPageable(), POPAGE.getTotalElements());

        return ResponseBodyBean.ofSuccess(ROPAGE);
    }

    @GetMapping(value = "get/{page_id}")
    public String getPage(@PathVariable(value = "page_id") Long pageId, Model model) {
        SystemPage page = pageService.getPageById(pageId);
        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListById(pageId);

        GetPageRO pageRO = new GetPageRO();
        BeanUtil.copyProperties(page, pageRO);
        List<GetPageRO.PagePermission> permissionROS = permissionRefs.stream().map(permissionRef -> {
            GetPageRO.PagePermission pagePermission = new GetPageRO.PagePermission();
            pagePermission.setPermissionId(permissionRef.getPermission().getId());
            pagePermission.setPermissionName(permissionRef.getPermission().getName());
            pagePermission.setPermissionCode(permissionRef.getPermission().getCode());
            pagePermission.setInterceptUrls(permissionRef.getInterceptUrls());
            return pagePermission;
        }).collect(Collectors.toList());
        pageRO.setPagePermissions(permissionROS);

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

        ModifyPageRO pageRO = new ModifyPageRO();
        BeanUtil.copyProperties(page, pageRO);
        List<ModifyPageRO.PagePermission> permissionROS = permissionRefs.stream().map(permissionRef -> {
            ModifyPageRO.PagePermission pagePermission = new ModifyPageRO.PagePermission();
            pagePermission.setPermissionId(permissionRef.getPermission().getId());
            pagePermission.setPermissionName(permissionRef.getPermission().getName());
            pagePermission.setInterceptUrls(permissionRef.getInterceptUrls());
            return pagePermission;
        }).collect(Collectors.toList());
        pageRO.setPagePermissions(permissionROS);

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
        List<GetPagePermissionListRO> permissionROS = new ArrayList<>();
        permissionRefs.forEach(permissionRef -> {
            GetPagePermissionListRO permissionRO = new GetPagePermissionListRO();
            permissionRO.setId(permissionRef.getId());
            permissionRO.setPermissionCode(permissionRef.getPermission().getCode());
            permissionRO.setPermissionName(permissionRef.getPermission().getName());
            permissionROS.add(permissionRO);
        });

        return ResponseBodyBean.ofSuccess(permissionROS);
    }
}
