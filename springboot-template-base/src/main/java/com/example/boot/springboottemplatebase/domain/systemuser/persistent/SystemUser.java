package com.example.boot.springboottemplatebase.domain.systemuser.persistent;

import com.baomidou.mybatisplus.annotation.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统用户
 *
 * @author chang_
 * @since 2019-11-22
 */
@Data
@Accessors(chain = true)
@TableName(value = "system_user")
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 用户描述
     */
    @TableField("description")
    private String description;

    /**
     * 最后一次登录时间
     */
    @TableField("last_login_time")
    private Timestamp lastLoginTime;

    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;

    /**
     * 登陆密码
     */
    @TableField("password")
    private String password;

    /**
     * 手机号码
     */
    @TableField("phone")
    private String phone;

    /**
     * 用户状态 1:正常, 2:已禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Timestamp updateTime;

    /**
     * 登陆账号
     */
    @TableField("username")
    private String username;

    /**
     * 系统角色ID
     */
    @TableField("role_id")
    private Long roleId;

    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 修改人
     */
    @TableField("update_by")
    private Long updateBy;

    /**
     * 删除标记(0:未删除,1:已删除)
     */
    @TableField("deleted")
    @TableLogic
    private String deleted;
}
