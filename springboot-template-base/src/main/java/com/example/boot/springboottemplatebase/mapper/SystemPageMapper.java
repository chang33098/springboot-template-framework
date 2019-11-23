package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systempage.persistent.SystemPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Repository
public interface SystemPageMapper extends BaseMapper<SystemPage> {

    String getPageCodeById(@Param(value = "pageId") Long pageId);
}
