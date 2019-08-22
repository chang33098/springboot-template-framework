package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.PagePermissionRef;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.role.constants.MenuLevel;
import com.example.boot.springboottemplatedomain.role.payload.*;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuPermissionRef;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
import com.example.boot.springboottemplatestarter.repository.RoleMenuPermissionRefRepository;
import com.example.boot.springboottemplatestarter.repository.RoleMenuRefRepository;
import com.example.boot.springboottemplatestarter.repository.RoleRepository;
import com.example.boot.springboottemplatestarter.service.PageService;
import com.example.boot.springboottemplatestarter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    private final RoleMenuPermissionRefRepository roleMenuPermissionRefRepository;

    private final PageService pageService;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMenuRefRepository roleMenuRefRepository, RoleMenuPermissionRefRepository roleMenuPermissionRefRepository, PageService pageService) {
        this.roleRepository = roleRepository;
        this.roleMenuRefRepository = roleMenuRefRepository;
        this.roleMenuPermissionRefRepository = roleMenuPermissionRefRepository;
        this.pageService = pageService;
    }

    @Override
    public Page<SystemRole> findRoleTable(FindRoleTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);
        Page<SystemRole> page = roleRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (!StringUtils.isEmpty(plo.getName())) {
                list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + plo.getName() + "%"));
            }

            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemRole getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));
    }

    @Override
    public void createRole(CreateRolePLO plo) {
        SystemRole role = new SystemRole();
        BeanUtil.copyProperties(plo, role);

        role.setCreateTime(new Timestamp(System.currentTimeMillis()));

        roleRepository.save(role);
    }

    @Override
    public void modifyRole(Long roleId, ModifyRolePLO plo) {
        SystemRole role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));
        BeanUtil.copyProperties(plo, role);

        role.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        SystemRole role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));
        roleRepository.delete(role);
    }

    @Override
    public List<RoleMenuRef> getRoleRootMenuListByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdAndMenuLevelOrderBySortNo(roleId, MenuLevel.PARENT_MENU.getType());
    }

    @Override
    public RoleMenuRef getRoleMenuByRoleIdAndMenuId(Long roleId, Long menuId) {
        return null;
    }

    @Override
    public void createRoleRootMenu(Long roleId, CreateRoleRootMenuPLO plo) {
        SystemRole role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));

        RoleMenuRef menuRef = new RoleMenuRef();
        menuRef.setIcon(plo.getIcon());
        menuRef.setMenuName(plo.getMenuName());
        menuRef.setMenuLevel(MenuLevel.PARENT_MENU.getType());
        menuRef.setRole(role);

        roleMenuRefRepository.save(menuRef);
    }

    // TODO: 2019/8/20 [createRoleMenu]功能未完善

    @Override
    public void createRoleSubMenu(Long roleId, CreateRoleSubMenuPLO plo) {
        final Long pageId = plo.getPageId();

        SystemRole role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));
        SystemPage page = pageService.getPageById(pageId);

        RoleMenuRef menuRef = new RoleMenuRef();
        BeanUtil.copyProperties(plo, menuRef);

        menuRef.setRole(role);
        menuRef.setPage(page);
        roleMenuRefRepository.save(menuRef);

        RoleMenuRef parent = roleMenuRefRepository.findById(plo.getParentId()).orElseThrow(() -> new ResourceNotFoundException("父菜单ID [" + plo.getParentId() + "] 不存在"));
        parent.getChildMenus().add(menuRef);
        roleMenuRefRepository.save(parent);

        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListByIds(plo.getPermissionIds());
        permissionRefs.forEach(permissionRef -> {
            RoleMenuPermissionRef menuPermissionRef = new RoleMenuPermissionRef();
            menuPermissionRef.setPermission(permissionRef);
            menuPermissionRef.setMenu(menuRef);
            roleMenuPermissionRefRepository.save(menuPermissionRef);
        });
    }

    @Override
    public void modifyRoleRootMenu(Long roleId, Long menuId, ModifyRoleRootMenuPLO plo) {
        RoleMenuRef menuRef = roleMenuRefRepository.findByIdAndRoleId(menuId, roleId).orElseThrow(() -> new ResourceNotFoundException("菜单ID [" + menuId + "] 不存在"));
        BeanUtil.copyProperties(plo, menuRef);

        roleMenuRefRepository.save(menuRef);
    }

    // TODO: 2019/8/20 [modifyRoleMenu]功能未完善
    
    @Override
    public void modifyRoleSubMenu(Long roleId, Long menuId, ModifyRoleSubMenuPLO plo) {
        final Long pageId = plo.getPageId();

        RoleMenuRef menuRef = roleMenuRefRepository.findByIdAndRoleId(menuId, roleId).orElseThrow(() -> new ResourceNotFoundException("菜单ID [" + menuId + "] 不存在"));
        SystemPage page = pageService.getPageById(pageId);

        menuRef.setIcon(plo.getIcon());
        menuRef.setMenuName(plo.getMenuName());
        menuRef.setSortNo(plo.getSortNo());
        menuRef.setPage(page);
        roleMenuRefRepository.save(menuRef);

        roleMenuPermissionRefRepository.deleteAllByMenuId(menuId);

        List<PagePermissionRef> permissionRefs = pageService.getPagePermissionListByIds(plo.getPermissionIds());
        permissionRefs.forEach(permissionRef -> {
            RoleMenuPermissionRef menuPermissionRef = new RoleMenuPermissionRef();
            menuPermissionRef.setPermission(permissionRef);
            menuPermissionRef.setMenu(menuRef);
            roleMenuPermissionRefRepository.save(menuPermissionRef);
        });
    }

    // TODO: 2019/8/20 [deleteRoleMenu] 功能有待完善

    @Override
    public void deleteRoleMenu(Long roleId, Long menuId) {
        RoleMenuRef menuRef = roleMenuRefRepository.findByIdAndRoleId(menuId, roleId).orElseThrow(() -> new ResourceNotFoundException("菜单ID [" + menuId + "] 不存在"));

        menuRef.getChildMenus().forEach(roleMenuRef -> deleteRoleMenu(roleId, roleMenuRef.getId()));

        roleMenuPermissionRefRepository.deleteAllByMenuId(menuId);

        roleMenuRefRepository.delete(menuRef);
    }

    @Override
    public List<RoleMenuRef> securityGetRoleMenuListByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
    }
}
