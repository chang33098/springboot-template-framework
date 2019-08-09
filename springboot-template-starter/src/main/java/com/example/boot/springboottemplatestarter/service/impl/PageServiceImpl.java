package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.example.boot.springboottemplatedomain.page.payload.CreatePagePLO;
import com.example.boot.springboottemplatedomain.page.payload.FindAllPagePLO;
import com.example.boot.springboottemplatedomain.page.payload.ModifyPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.PageRepository;
import com.example.boot.springboottemplatestarter.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
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
public class PageServiceImpl implements PageService {

    private final PageRepository pageRepository;

    @Autowired
    public PageServiceImpl(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    @Override
    public Page<SystemPage> findAllPage(FindAllPagePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);

        Page<SystemPage> page = pageRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemPage getPageById(Long pageId) {
        return pageRepository.findById(pageId).orElseThrow(() -> {
            log.error("页面ID [{}] 不存在", pageId);
            return new ResourceNotFoundException("页面ID [" + pageId + "] 不存在");
        });
    }

    @Override
    public void createPage(CreatePagePLO plo) {
        SystemPage page = new SystemPage();
        BeanUtil.copyProperties(plo, page);

        pageRepository.save(page);
    }

    @Override
    public void modifyPage(Long pageId, ModifyPagePLO plo) {
        SystemPage page = pageRepository.findById(pageId).orElseThrow(() -> {
            log.error("页面ID [{}] 不存在", pageId);
            return new ResourceNotFoundException("页面ID [" + pageId + "] 不存在");
        });

        BeanUtil.copyProperties(plo, page);

        pageRepository.save(page);
    }

    @Override
    public void deletePage(Long pageId) {
        SystemPage page = pageRepository.findById(pageId).orElseThrow(() -> {
            log.error("页面ID [{}] 不存在", pageId);
            return new ResourceNotFoundException("页面ID [" + pageId + "] 不存在");
        });

        pageRepository.delete(page);
    }
}
