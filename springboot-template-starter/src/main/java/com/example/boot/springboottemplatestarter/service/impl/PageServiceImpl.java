package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.page.payload.FindAllPagePLO;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatestarter.repository.PageRepository;
import com.example.boot.springboottemplatestarter.service.PageService;
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
}
