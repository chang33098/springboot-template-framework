package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatedomain.page.persistent.SystemPagePermissionRef;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Repository
public interface SystemPagePermissionRefMapper extends BaseMapper<SystemPagePermissionRef> {

    void deleteRefByPageId(@Param(value = "pageId") Long pageId);
}
