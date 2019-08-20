package com.example.boot.springboottemplatestarter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by ANdady on 2019/7/22.
 */
@Repository
public interface RoleMenuRefRepository extends JpaRepository<RoleMenuRef, Long>, JpaSpecificationExecutor<RoleMenuRef> {

    List<RoleMenuRef> findAllByRoleIdOrderBySortNoAsc(Long roleId);

    Optional<RoleMenuRef> findByIdAndRoleId(Long menuId, Long roleId);
}
