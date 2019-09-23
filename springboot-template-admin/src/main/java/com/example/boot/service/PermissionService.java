package com.example.boot.service;

import com.example.boot.model.permission.payload.CreatePermissionPLO;
import com.example.boot.model.permission.payload.FindPermissionTablePLO;
import com.example.boot.model.permission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatedomain.permission.persistent.SystemPermission;
import org.springframework.data.domain.Page;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/3 17:07
 */
public interface PermissionService {

    Page<SystemPermission> findPermissionTable(FindPermissionTablePLO plo);

    SystemPermission getPermissionById(Long permissionId);

    void createPermission(CreatePermissionPLO plo);

    void modifyPermission(Long permissionId, ModifyPermissionPLO plo);

    void deletePermission(Long permissionId);
}
