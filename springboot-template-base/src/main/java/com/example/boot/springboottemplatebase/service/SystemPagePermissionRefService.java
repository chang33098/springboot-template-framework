package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempage.entity.SystemPagePermissionRefEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetPagePermissionListVO;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPagePermissionRefService extends IService<SystemPagePermissionRefEntity> {

    List<SecurityGetPagePermissionListVO> securityGetPagePermissionList();
}
