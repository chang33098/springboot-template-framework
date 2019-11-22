package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemuser.persistent.SystemUser;
import org.springframework.stereotype.Repository;

/**
 * @author chang_
 * @since 2019-11-22
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

}
