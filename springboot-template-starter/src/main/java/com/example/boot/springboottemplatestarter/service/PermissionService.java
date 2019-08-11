package com.example.boot.springboottemplatestarter.service;

import com.example.boot.springboottemplatedomain.permission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatedomain.permission.payload.FindAllPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermissionUrl;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 17:07
 */
public interface PermissionService {

    Page<SystemPermission> findAllPermission(FindAllPermissionPLO plo);

    void createPermission(CreatePermissionPLO plo);

    void modifyPermission(Long permissionId, ModifyPermissionPLO plo);

    void deletePermission(Long permissionId);

    List<SystemPermissionUrl> findAllPermissionUrl();
}
