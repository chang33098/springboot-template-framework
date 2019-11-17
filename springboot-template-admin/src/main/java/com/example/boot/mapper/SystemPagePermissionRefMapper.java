package com.example.boot.mapper;

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

    /**
     * 通过页面ID删除
     *
     * @param pageId 页面ID
     */
    void deleteRefByPageId(@Param(value = "pageId") Long pageId);
}
