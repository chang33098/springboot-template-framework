package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface RoleMenuRefRepository extends JpaRepository<RoleMenuRef, Long>, JpaSpecificationExecutor<RoleMenuRef> {
}
