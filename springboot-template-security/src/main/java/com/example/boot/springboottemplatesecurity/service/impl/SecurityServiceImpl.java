package com.example.boot.springboottemplatesecurity.service.impl;

import com.example.boot.springboottemplatesecurity.service.SecurityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/23 22:38
 */
@Service
@Transactional
public class SecurityServiceImpl implements SecurityService {

//    private final UserRepository userRepository;
//    private final RoleMenuRefRepository roleMenuRefRepository;
//    private final RoleMenuPermissionRefRepository roleMenuPermissionRefRepository;
//    private final PagePermissionRefRepository pagePermissionRefRepository;
//
//    @Autowired
//    public SecurityServiceImpl(UserRepository userRepository,
//                               RoleMenuRefRepository roleMenuRefRepository,
//                               RoleMenuPermissionRefRepository roleMenuPermissionRefRepository,
//                               PagePermissionRefRepository pagePermissionRefRepository) {
//        this.userRepository = userRepository;
//        this.roleMenuRefRepository = roleMenuRefRepository;
//        this.roleMenuPermissionRefRepository = roleMenuPermissionRefRepository;
//        this.pagePermissionRefRepository = pagePermissionRefRepository;
//    }

//    @Override
//    public Optional<SystemUser> securityGetUserByUsername(String username) {
////        return userRepository.findByUsername(username);
//
//        return null;
//    }
//
//    @Override
//    public List<RoleMenuRef> securityGetRoleMenuListByRoleId(Long roleId) {
////        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
//
//        return null;
//    }
//
//    @Override
//    public List<RoleMenuPermissionRef> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIds) {
////        return roleMenuPermissionRefRepository.findAllByMenuIdIn(menuIds);
//        return null;
//    }
//
//    @Override
//    public List<PagePermissionRef> securityGetPagePermissionList() {
////        return pagePermissionRefRepository.findAll();
//        return null;
//    }
}
