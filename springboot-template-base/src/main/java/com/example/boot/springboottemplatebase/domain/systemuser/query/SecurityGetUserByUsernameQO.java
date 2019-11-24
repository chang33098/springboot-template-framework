package com.example.boot.springboottemplatebase.domain.systemuser.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * service、mapper方法[securityGetUserByUsername]返回的查询对象
 * tips: 以'QO'为后缀的对象只用于查询所使用的
 *
 * @author Chang
 * @date 2019/11/24 13:49
 */
@Data
public class SecurityGetUserByUsernameQO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "role_id")
    private Long roleId;

    @TableField(value = "role_name")
    private String roleName;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "description")
    private String description;

    @TableField(value = "status")
    private String status;

    @TableField(value = "create_time")
    private Timestamp createTime;

    @TableField(value = "update_time")
    private Timestamp updateTime;

    @TableField(value = "last_login_time")
    private Timestamp lastLoginTime;
}
