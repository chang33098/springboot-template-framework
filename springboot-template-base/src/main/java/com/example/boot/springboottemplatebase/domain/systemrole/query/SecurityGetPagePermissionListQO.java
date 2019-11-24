package com.example.boot.springboottemplatebase.domain.systemrole.query;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * service、mapper[securityGetPagePermissionList]所返回的查询对象
 * tips: 以'QO'为后缀的对象只用于查询所使用的
 *
 * @author Chang
 * @date 2019/11/24 11:34
 */
@Data
public class SecurityGetPagePermissionListQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限所拦截的URL
     */
    @TableField(value = "intercept_urls")
    private String interceptUrls;

    /**
     * 页面代码
     */
    @TableField(value = "page_code")
    private String pageCode;

    /**
     * 权限代码
     */
    @TableField(value = "permission_code")
    private String permissionCode;
}
