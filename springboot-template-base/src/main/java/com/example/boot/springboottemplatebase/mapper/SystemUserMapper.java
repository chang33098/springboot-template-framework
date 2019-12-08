package com.example.boot.springboottemplatebase.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.boot.springboottemplatebase.domain.systemuser.entity.SystemUser;
import com.example.boot.springboottemplatebase.domain.systemuser.value.SecurityGetUserByUsernameVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author chang_
 * @since 2019-11-22
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {

    Optional<SecurityGetUserByUsernameVO> securityGetUserByUsername(@Param(value = "username") String username);
}
