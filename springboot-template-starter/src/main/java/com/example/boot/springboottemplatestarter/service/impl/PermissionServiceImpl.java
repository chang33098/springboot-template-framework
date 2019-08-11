package com.example.boot.springboottemplatestarter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.boot.springboottemplatedomain.permission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatedomain.permission.payload.FindAllPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import com.example.boot.springboottemplatestarter.repository.PermissionRepository;
import com.example.boot.springboottemplatestarter.repository.PermissionUrlRepository;
import com.example.boot.springboottemplatestarter.service.PermissionService;
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
    private final PermissionUrlRepository permissionUrlRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionUrlRepository permissionUrlRepository) {
        this.permissionRepository = permissionRepository;
        this.permissionUrlRepository = permissionUrlRepository;
    }

    @Override
    public Page<SystemPermission> findAllPermission(FindAllPermissionPLO plo) {
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
    public void createPermission(CreatePermissionPLO plo) {
        SystemPermission permission = new SystemPermission();
        BeanUtil.copyProperties(plo, permission);

        permission.setCreateTime(new Timestamp(System.currentTimeMillis()));
        permissionRepository.save(permission);

        plo.getMatchUrls().forEach(data -> {
            SystemPermissionUrl permissionUrl = new SystemPermissionUrl();
            permissionUrl.setMatchUrl(data.getMatchUrl());
            permissionUrl.setPermission(permission);
            permissionUrl.setSortNo(data.getSortNo());

            permissionUrlRepository.save(permissionUrl);
        });
    }

    @Override
    public void modifyPermission(Long permissionId, ModifyPermissionPLO plo) {

    }

    @Override
    public void deletePermission(Long permissionId) {

    }

    @Override
    public List<SystemPermissionUrl> findAllPermissionUrl() {
        return permissionUrlRepository.findAll();
    }
}
