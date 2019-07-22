package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;

/**
 * Created by ANdady on 2019/7/22.
 */
public interface RoleMenuRefRepository extends JpaRepository<RoleMenuRef, Long> {
}
