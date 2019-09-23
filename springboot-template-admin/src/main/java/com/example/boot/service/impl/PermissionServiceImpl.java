package com.example.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.exception.ResourceNotFoundException;
import com.example.boot.model.permission.payload.CreatePermissionPLO;
import com.example.boot.model.permission.payload.FindPermissionTablePLO;
import com.example.boot.model.permission.payload.ModifyPermissionPLO;
import com.example.boot.service.PermissionService;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplaterepository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 17:07
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Page<SystemPermission> findPermissionTable(FindPermissionTablePLO plo) {
        int pageNo = plo.getPageNo();
        int limit = plo.getLimit();

        Pageable pageable = PageRequest.of(pageNo - 1, limit);

        Page<SystemPermission> page = permissionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            Predicate[] predicates = new Predicate[list.size()];
            return criteriaQuery.where(list.toArray(predicates)).getRestriction();
        }, pageable);

        return page;
    }

    @Override
    public SystemPermission getPermissionById(Long permissionId) {
        return permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("权限ID [" + permissionId + "] 不存在"));
    }

    @Override
    public void createPermission(CreatePermissionPLO plo) {
        SystemPermission permission = new SystemPermission();
        BeanUtil.copyProperties(plo, permission);

        permission.setCreateTime(new Timestamp(System.currentTimeMillis()));
        permissionRepository.save(permission);
    }

    @Override
    public void modifyPermission(Long permissionId, ModifyPermissionPLO plo) {
        SystemPermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("权限ID [" + permissionId + "] 不存在"));
        BeanUtil.copyProperties(plo, permission);

        permission.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long permissionId) {
        SystemPermission permission = permissionRepository.findById(permissionId)
                .orElseThrow(() -> new ResourceNotFoundException("权限ID [" + permissionId + "] 不存在"));
        permissionRepository.delete(permission);
    }
}
