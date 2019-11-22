package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuPermissionRef;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
public interface SystemRoleMenuPermissionRefService extends IService<SystemRoleMenuPermissionRef> {

    List<SystemRoleMenuPermissionRef> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIdList);
}
