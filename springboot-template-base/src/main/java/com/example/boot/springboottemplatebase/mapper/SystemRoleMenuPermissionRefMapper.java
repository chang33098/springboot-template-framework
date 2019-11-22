package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRoleMenuPermissionRef;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Repository
public interface SystemRoleMenuPermissionRefMapper extends BaseMapper<SystemRoleMenuPermissionRef> {

    void deleteAllByRoleId(Long roleId);
}
