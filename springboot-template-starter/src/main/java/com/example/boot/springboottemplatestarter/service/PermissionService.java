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

    // TODO: 2019/8/12 添加service方法的注解

    Page<SystemPermission> findAllPermission(FindAllPermissionPLO plo);

    SystemPermission getPermissionById(Long permissionId);

    List<SystemPermissionUrl> getPermissionsByPermissionId(Long permissionId);

    void createPermission(CreatePermissionPLO plo);

    void modifyPermission(Long permissionId, ModifyPermissionPLO plo);

    void deletePermission(Long permissionId);

    /**
     * [spring security] 获取所有的权限拦截的URL
     *
     * @return
     */
    List<SystemPermissionUrl> securityGetAllPermissionUrl();
}
