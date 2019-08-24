package com.example.boot.springboottemplatestarter.repository;

import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by EDZ on 2019/8/20.
 */
@Repository
public interface RoleMenuPermissionRefRepository extends JpaRepository<RoleMenuPermissionRef, Long> {

    void deleteAllByMenuId(Long menuId);

    void deleteAllByPermissionId(Long pagePermissionId);

    List<RoleMenuPermissionRef> findAllByMenuId(Long menuId);

    List<RoleMenuPermissionRef> findAllByMenuIdIn(List<Long> menuId);
}
