package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPagePermissionRef;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetPagePermissionListVO;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-16
 */
public interface SystemPagePermissionRefService extends IService<SystemPagePermissionRef> {

    List<SecurityGetPagePermissionListVO> securityGetPagePermissionList();
}
