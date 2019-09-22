package com.example.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface PageRepository extends JpaRepository<SystemPage, Long>, JpaSpecificationExecutor<SystemPage> {
}
