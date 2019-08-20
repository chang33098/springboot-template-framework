package com.example.boot.springboottemplatestarter.repository;

import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by EDZ on 2019/8/20.
 */
@Repository
public interface RoleMenuPermissionRefRepository extends JpaRepository<RoleMenuPermissionRef, Long> {

    void deleteAllByMenuId(Long menuId);
}
