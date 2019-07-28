package com.example.boot.springboottemplatestarter.service;

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
     *
     * @param pageNo
     * @param limit
     * @return
     */
    Page<SystemPage> findAllPage(int pageNo, int limit);
}
