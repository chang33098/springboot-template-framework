package com.example.boot.springboottemplatebase.domain.systemuser.entity;

import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.io.Serializable;

import com.example.boot.springboottemplatebase.base.persistent.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>系统用户entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_user")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemUserEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 登陆账号
     */
    @Column(columnDefinition = "varchar(50) default null comment '登录账号'")
    private String username;

    /**
     * 登陆密码
     */
    @Column(columnDefinition = "varchar(50) default null comment '登录密码(采用BCrypt进行加密)'")
    private String password;

    /**
     * 头像
     */
    @Column(columnDefinition = "varchar(100) default null comment '用户头像'")
    private String avatar;

    /**
     * 昵称
     */
    @Column(columnDefinition = "varchar(255) default null comment '用户昵称'")
    private String nickname;

    /**
     * 手机号码
     */
    @Column(columnDefinition = "varchar(20) default null comment '手机号码'")
    private String phone;

    /**
     * 用户状态 1:正常, 2:已禁用
     */
    @Column(columnDefinition = "varchar(5) default '1' comment '用户状态(1:正常,2:已禁用)'")
    private String status;

    /**
     * 用户描述
     */
    @Column(columnDefinition = "varchar(255) default null comment '用户描述'")
    private String description;

    /**
     * 最后一次登录时间
     */
    @Column(columnDefinition = "datetime default null comment '最后一次登录时间'")
    private Timestamp lastLoginTime;

    /**
     * 系统角色ID
     */
    @Column(columnDefinition = "bigint default null comment '系统角色ID'")
    private Long roleId;
}
