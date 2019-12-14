package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemrole.entity.SystemRoleMenuRefEntity;
import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetRoleMenuListByRoleIdVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-17
 */
@Repository
public interface SystemRoleMenuRefMapper extends BaseMapper<SystemRoleMenuRefEntity> {

    void deleteAllByRoleId(@Param(value = "roleId") Long roleId);

    List<SecurityGetRoleMenuListByRoleIdVO> securityGetRoleRootMenuListByRoleId(@Param(value = "roleId") Long roleId);

    List<SecurityGetRoleMenuListByRoleIdVO> securityGetRoleChildrenMenuListByParentId(@Param(value = "parentId") Long parentId);
}
