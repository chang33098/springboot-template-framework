package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.payload.CreatePagePLO;
import com.example.boot.springboottemplatedomain.page.payload.FindPageTablePLO;
import com.example.boot.springboottemplatedomain.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.PagePermissionRefRepository;
import com.example.boot.springboottemplatestarter.repository.PageRepository;
import com.example.boot.springboottemplatestarter.service.PageService;
import com.example.boot.springboottemplatestarter.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:05
 */
@Slf4j
@Service
@Transactional
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;
    private final PagePermissionRefRepository pagePermissionRefRepository;

    private final PermissionService permissionService;

    @Autowired
    public PageServiceImpl(PageRepository pageRepository, PagePermissionRefRepository pagePermissionRefRepository, PermissionService permissionService) {
        this.pageRepository = pageRepository;
        this.pagePermissionRefRepository = pagePermissionRefRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Page<SystemPage> findPageTable(FindPageTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);

        Page<SystemPage> page = pageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(plo.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + plo.getName() + "%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemPage getPageById(Long pageId) {
        return pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("页面ID [" + pageId + "] 不存在"));
    }

    @Override
    public List<PagePermissionRef> getPagePermissionListById(Long pageId) {
        return pagePermissionRefRepository.findAllByPageId(pageId);
    }

    @Override
    public void createPage(CreatePagePLO plo) {
        SystemPage page = new SystemPage();
        BeanUtil.copyProperties(plo, page);

        page.setCreateTime(new Timestamp(System.currentTimeMillis()));
        pageRepository.save(page);

        plo.getPagePermissions().forEach(pagePermission -> {
            PagePermissionRef ref = new PagePermissionRef();

            SystemPermission permission = permissionService.getPermissionById(pagePermission.getPermissionId());
            ref.setPermission(permission);
            ref.setInterceptUrls(pagePermission.getInterceptUrls());
            ref.setPage(page);

            pagePermissionRefRepository.save(ref);
        });
    }

    @Override
    public void modifyPage(Long pageId, ModifyPagePLO plo) {
        SystemPage page = pageRepository.findById(pageId).orElseThrow(() -> new ResourceNotFoundException("页面ID [" + pageId + "] 不存在"));
        BeanUtil.copyProperties(plo, page);

        page.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        pageRepository.save(page);

        pagePermissionRefRepository.deleteAllByPageId(pageId); //删除旧关联

        plo.getPagePermissions().forEach(pagePermission -> {
            PagePermissionRef ref = new PagePermissionRef();

            SystemPermission permission = permissionService.getPermissionById(pagePermission.getPermissionId());
            ref.setPermission(permission);
            ref.setInterceptUrls(pagePermission.getInterceptUrls());
            ref.setPage(page);

            pagePermissionRefRepository.save(ref);
        });
    }

    @Override
    public void deletePage(Long pageId) {
        SystemPage page = pageRepository.findById(pageId).orElseThrow(() -> {
            log.error("页面ID [{}] 不存在", pageId);
            return new ResourceNotFoundException("页面ID [" + pageId + "] 不存在");
        });

        pagePermissionRefRepository.deleteAllByPageId(pageId);
        pageRepository.delete(page);
    }

    @Override
    public List<PagePermissionRef> getPagePermissionListByIds(List<Long> refIds) {
        return pagePermissionRefRepository.findAllByIdIsIn(refIds);
    }
}
