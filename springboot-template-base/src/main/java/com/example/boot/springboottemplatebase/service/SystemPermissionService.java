package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.persistent.SystemPermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPermissionService extends IService<SystemPermission> {

    void create(CreatePermissionPLO permissionPLO);

    void modify(ModifyPermissionPLO permissionPLO);

    void delete(Long permissionId);

    String getPermissionCodeById(Long permissionId);
}
