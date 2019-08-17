package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.role.payload.CreateRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.FindAllRolePLO;
import com.example.boot.springboottemplatedomain.role.payload.ModifyRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;
import com.example.boot.springboottemplatestarter.repository.RoleMenuRefRepository;
import com.example.boot.springboottemplatestarter.repository.RoleRepository;
import com.example.boot.springboottemplatestarter.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
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

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, RoleMenuRefRepository roleMenuRefRepository) {
        this.roleRepository = roleRepository;
        this.roleMenuRefRepository = roleMenuRefRepository;
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
        return null;
    }

    @Override
    public void createRole(CreateRolePLO plo) {

    }

    @Override
    public void modifyRole(ModifyRolePLO plo) {

    }

    @Override
    public void deleteRole(Long id) {

    }

    @Override
    public List<RoleMenuRef> securityGetAllRoleMenuByRoleId(Long roleId) {
        return roleMenuRefRepository.findAllByRoleIdOrderBySortNoAsc(roleId);
    }
}
