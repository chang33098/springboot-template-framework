package com.example.boot.springboottemplaterepository;

import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ANdady on 2019/8/15.
 */
public interface PagePermissionRefRepository extends JpaRepository<PagePermissionRef, Long> {

    void deleteAllByPageId(Long pageId);

    List<PagePermissionRef> findAllByPageId(Long pageId);

    List<PagePermissionRef> findAllByIdIsIn(List<Long> refIds);
}