package com.example.boot.springboottemplatestarter.service.impl;

import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import com.example.boot.springboottemplatestarter.repository.PermissionRepository;
import com.example.boot.springboottemplatestarter.repository.PermissionUrlRepository;
import com.example.boot.springboottemplatestarter.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<SystemPermissionUrl> findAllPermissionUrl() {
        return permissionUrlRepository.findAll();
    }
}
