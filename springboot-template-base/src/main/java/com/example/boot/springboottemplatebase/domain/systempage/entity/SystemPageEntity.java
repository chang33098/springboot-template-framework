package com.example.boot.springboottemplatebase.domain.systempage.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * <p>系统页面entity</p>
 *
 * @author chang_
 * @since 2019-11-16
 */
@Data
@Entity
@Table(name = "system_page")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemPageEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模块代码(由英文和下划线组成)
     */
    @Column(columnDefinition = "varchar(50) default null comment '页面代码'")
    private String pageCode;

    /**
     * 页面名称
     */
    @Column(columnDefinition = "varchar(50) default null comment '页面名称' ")
    private String pageName;

    /**
     * 页面访问链接
     */
    @Column(columnDefinition = "varchar(255) default null comment '页面链接'")
    private String pageUrl;

    /**
     * 页面作用描述
     */
    @Column(columnDefinition = "varchar(255) default null comment '页面作用描述'")
    private String description;
}
