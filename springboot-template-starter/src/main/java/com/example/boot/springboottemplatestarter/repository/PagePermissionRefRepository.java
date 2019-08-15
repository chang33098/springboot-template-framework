package com.example.boot.springboottemplatestarter.repository;

import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ANdady on 2019/8/15.
 */
public interface PagePermissionRefRepository extends JpaRepository<PagePermissionRef, Long> {
}
