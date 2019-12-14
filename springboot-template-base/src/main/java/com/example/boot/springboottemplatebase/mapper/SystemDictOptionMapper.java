package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemdict.entity.SystemDictOptionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-13
 */
@Repository
public interface SystemDictOptionMapper extends BaseMapper<SystemDictOptionEntity> {

    void deleteAllByDictId(@Param(value = "dictId") Long dictId);
}
