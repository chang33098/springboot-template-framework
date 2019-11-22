package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.response.role.payload.CreateRolePLO;
import com.example.boot.response.role.payload.ModifyRolePLO;
import com.example.boot.springboottemplatedomain.role.persistent.SystemRole;

/**
 * @author chang_
 * @since 2019-11-17
 */
public interface SystemRoleService extends IService<SystemRole> {

    void create(CreateRolePLO rolePLO);

    void modify(ModifyRolePLO rolePLO);

    void delete(Long roleId);
}
