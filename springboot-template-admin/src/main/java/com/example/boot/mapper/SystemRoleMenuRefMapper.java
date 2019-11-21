package com.example.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRoleMenuRef;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Repository
public interface SystemRoleMenuRefMapper extends BaseMapper<SystemRoleMenuRef> {

    void deleteAllByRoleId(@Param(value = "roleId") Long roleId);
}
