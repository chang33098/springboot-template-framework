package com.example.boot.springboottemplatebase.domain.systempage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.example.boot.springboottemplatebase.base.persistent.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_page_permission_ref")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemPagePermissionRef extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限所拦截的URL
     */
    @Column(columnDefinition = "varchar(255) ")
    private String interceptUrls;

    /**
     * 系统页面ID
     */
    @Column(columnDefinition = "bigint default null comment '系统页面ID(system_page.id)'")
    private Long pageId;

    /**
     * 系统权限ID
     */
    @Column(columnDefinition = "bigint default null comment '系统权限ID(system_permission.id)'")
    private Long permissionId;
}
