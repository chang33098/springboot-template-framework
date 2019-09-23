package com.example.boot.springboottemplatesecurity.service.impl;

import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.user.persistent.SystemUser;
import com.example.boot.springboottemplaterepository.PagePermissionRefRepository;
import com.example.boot.springboottemplaterepository.RoleMenuPermissionRefRepository;
import com.example.boot.springboottemplaterepository.RoleMenuRefRepository;
import com.example.boot.springboottemplaterepository.UserRepository;
import com.example.boot.springboottemplatesecurity.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/23 22:38
 */
@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;
    private final RoleMenuRefRepository roleMenuRefRepository;
    private final RoleMenuPermissionRefRepository roleMenuPermissionRefRepository;
    private final PagePermissionRefRepository pagePermissionRefRepository;

    @Autowired
    public SecurityServiceImpl(UserRepository userRepository,
                               RoleMenuRefRepository roleMenuRefRepository,
                               RoleMenuPermissionRefRepository roleMenuPermissionRefRepository,
                               PagePermissionRefRepository pagePermissionRefRepository) {
        this.userRepository = userRepository;
        this.roleMenuRefRepository = roleMenuRefRepository;
        this.roleMenuPermissionRefRepository = roleMenuPermissionRefRepository;
        this.pagePermissionRefRepository = pagePermissionRefRepository;
    }

    @Override
    public Optional<SystemUser> securityGetUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<RoleMenuRef> securityGetRoleMenuListByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
    }

    @Override
    public List<RoleMenuPermissionRef> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIds) {
        return roleMenuPermissionRefRepository.findAllByMenuIdIn(menuIds);
    }

    @Override
    public List<PagePermissionRef> securityGetPagePermissionList() {
        return pagePermissionRefRepository.findAll();
    }
}
