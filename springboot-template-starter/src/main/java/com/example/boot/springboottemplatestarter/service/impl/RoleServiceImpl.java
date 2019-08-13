package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatestarter.repository.RoleMenuRefRepository;
import com.example.boot.springboottemplatestarter.repository.RoleRepository;
import com.example.boot.springboottemplatestarter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/28 15:05
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMenuRefRepository roleMenuRefRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMenuRefRepository roleMenuRefRepository) {
        this.roleRepository = roleRepository;
        this.roleMenuRefRepository = roleMenuRefRepository;
    }

    @Override
    public List<SystemRole> getAllRole() {
        return null;
    }

    @Override
    public List<RoleMenuRef> securityGetAllRoleMenuByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
    }
}
