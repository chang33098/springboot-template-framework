package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleMenuPermissionRefEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuPermissionListByMenuIdsVO;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
public interface SystemRoleMenuPermissionRefService extends IService<SystemRoleMenuPermissionRefEntity> {

    List<SecurityGetRoleMenuPermissionListByMenuIdsVO> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIdList);
}
