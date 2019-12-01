package com.example.boot.springboottemplatebase.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.CreateRolePLO;
import com.example.boot.springboottemplatebase.domain.systemrole.payload.ModifyRolePLO;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRole;

/**
 * @author chang_
 * @since 2019-11-17
 */
public interface SystemRoleService extends IService<SystemRole> {

    void create(CreateRolePLO payload);

    void modify(ModifyRolePLO payload);

    void delete(Long roleId);
}
