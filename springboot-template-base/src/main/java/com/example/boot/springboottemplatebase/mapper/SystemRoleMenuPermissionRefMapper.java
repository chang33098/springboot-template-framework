package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemrole.persistent.SystemRoleMenuPermissionRef;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuPermissionListByMenuIdsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Repository
public interface SystemRoleMenuPermissionRefMapper extends BaseMapper<SystemRoleMenuPermissionRef> {

    void deleteAllByRoleId(Long roleId);

    List<SecurityGetRoleMenuPermissionListByMenuIdsVO> securityGetRoleMenuPermissionListByMenuIds(List<Long> menuIdList);
}