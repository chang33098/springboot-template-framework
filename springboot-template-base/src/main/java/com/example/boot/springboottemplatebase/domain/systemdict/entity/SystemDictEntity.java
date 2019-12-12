package com.example.boot.springboottemplatebase.domain.systemdict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>数据字典entity</p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Data
@Entity
@Table(name = "system_dict")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemDictEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典类型(由大写英文和下划线组成)
     */
    @Column(columnDefinition = "varchar(20) default null comment '字典类型(由大写英文和下划线组成)'")
    private String dictCode;

    /**
     * 字典名称
     */
    @Column(columnDefinition = "varchar(100) default null comment '字典名称'")
    private String dictName;

    /**
     * 字典描述
     */
    @Column(columnDefinition = "varchar(255) default null comment '字典作用描述'")
    private String description;
}
