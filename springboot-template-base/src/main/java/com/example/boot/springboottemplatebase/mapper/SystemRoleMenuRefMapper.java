package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuRef;
import com.example.boot.springboottemplatebase.domain.systemrole.query.SecurityGetRoleMenuListByRoleIdRO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Repository
public interface SystemRoleMenuRefMapper extends BaseMapper<SystemRoleMenuRef> {

    void deleteAllByRoleId(@Param(value = "roleId") Long roleId);

    List<SecurityGetRoleMenuListByRoleIdRO> securityGetRoleMenuListByRoleId(@Param(value = "roleId") Long roleId);
}
