package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.CreatePermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.payload.ModifyPermissionPLO;
import com.example.boot.springboottemplatebase.domain.systempermission.entity.SystemPermissionEntity;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPermissionService extends IService<SystemPermissionEntity> {

    void create(CreatePermissionPLO payload);

    void modify(ModifyPermissionPLO payload);

    void delete(Long permissionId);
}
