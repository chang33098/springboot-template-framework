package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuListByRoleIdVO;
import com.example.boot.springboottemplatebase.mapper.SystemRoleMenuRefMapper;
import com.example.boot.springboottemplatebase.service.SystemRoleMenuRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuRef;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Service
public class SystemRoleMenuRefServiceImpl extends ServiceImpl<SystemRoleMenuRefMapper, SystemRoleMenuRef> implements SystemRoleMenuRefService {

    @Override
    public List<SecurityGetRoleMenuListByRoleIdVO> securityGetRoleMenuListByRoleId(Long roleId) {
        return this.getBaseMapper().securityGetRoleRootMenuListByRoleId(roleId);
    }
}
