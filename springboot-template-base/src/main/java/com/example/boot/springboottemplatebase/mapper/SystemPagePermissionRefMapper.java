package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPagePermissionRef;
import com.example.boot.springboottemplatebase.domain.systemrole.query.SecurityGetPagePermissionListQO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Repository
public interface SystemPagePermissionRefMapper extends BaseMapper<SystemPagePermissionRef> {

    void deleteRefByPageId(@Param(value = "pageId") Long pageId);

    List<SecurityGetPagePermissionListQO> securityGetPagePermissionList();
}
