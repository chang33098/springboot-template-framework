package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;

import java.util.List;

/**
 * Created by ANdady on 2019/7/28.
 */
public interface RoleService {

    List<RoleMenuRef> findAllByRoleIdOrderBySortNoAsc(Long roleId);
}
