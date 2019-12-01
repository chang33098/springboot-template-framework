package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuPermissionListByMenuIdsVO;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuPermissionRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuPermissionRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuPermissionRef;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleMenuPermissionRefServiceImpl extends ServiceImpl<SystemRoleMenuPermissionRefMapper, SystemRoleMenuPermissionRef> implements SystemRoleMenuPermissionRefService {

    @Override
    public List<SecurityGetRoleMenuPermissionListByMenuIdsVO> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIdList) {
        return this.getBaseMapper().securityGetRoleMenuPermissionListByMenuIds(menuIdList);
    }
}
