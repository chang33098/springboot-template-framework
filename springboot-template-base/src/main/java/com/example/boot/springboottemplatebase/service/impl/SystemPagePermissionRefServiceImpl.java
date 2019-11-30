package com.example.boot.springboottemplatebase.service.impl;

import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetPagePermissionListVO;
import com.example.boot.springboottemplatebase.mapper.SystemPagePermissionRefMapper;
import com.example.boot.springboottemplatebase.service.SystemPagePermissionRefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPagePermissionRef;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Service
@Transactional
public class SystemPagePermissionRefServiceImpl extends ServiceImpl<SystemPagePermissionRefMapper, SystemPagePermissionRef> implements SystemPagePermissionRefService {
    
    @Override
    public List<SecurityGetPagePermissionListVO> securityGetPagePermissionList() {
        return this.baseMapper.securityGetPagePermissionList();
    }
}
