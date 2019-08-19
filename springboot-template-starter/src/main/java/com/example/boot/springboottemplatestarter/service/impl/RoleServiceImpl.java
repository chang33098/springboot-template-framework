package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPage;
import com.example.boot.springboottemplatedomain.role.payload.CreateRoleMenuPLO;
import com.example.boot.springboottemplatedomain.role.payload.CreateRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.FindAllRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.ModifyRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatestarter.exception.ResourceNotFoundException;
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

    private final PageService pageService;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMenuRefRepository roleMenuRefRepository, PageService pageService) {
        this.roleRepository = roleRepository;
        this.roleMenuRefRepository = roleMenuRefRepository;
        this.pageService = pageService;
    }

    @Override
    public Page<SystemRole> findAllRole(FindAllRolePLO plo) {
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
    public void createRoleMenu(CreateRoleMenuPLO plo) {

//        @NotNull
//        private Long roleId;
//        @NotNull
//        private Long pageId;
//        private String icon;
//        @NotNull
//        private String menuName;
//        @NotNull
//        private Integer menuLevel;
//        private Long parentId;
//        @NotEmpty
//        @Size(min = 1)
//        private List<Long> permissionIds = new ArrayList<>();

        final Long roleId = plo.getRoleId();
        final Long pageId = plo.getPageId();

        SystemRole role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("角色ID [" + roleId + "] 不存在"));
        SystemPage page = pageService.getPageById(pageId);

        RoleMenuRef menuRef = new RoleMenuRef();
        BeanUtil.copyProperties(plo, menuRef);

        menuRef.setRole(role);
    }

    @Override
    public List<RoleMenuRef> securityGetAllRoleMenuByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
    }
}
