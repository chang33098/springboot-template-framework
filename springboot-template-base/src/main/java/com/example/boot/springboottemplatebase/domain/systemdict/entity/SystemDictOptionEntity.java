package com.example.boot.springboottemplatebase.domain.systemdict.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boot.springboottemplatebase.base.persistent.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <p>数据字典项entity</p>
 *
 * @author chang_
 * @since 2019-11-13
 */
@Data
@Entity
@Table(name = "system_dict_option")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SystemDictOptionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 选项代码
     */
    @Column(columnDefinition = "varchar(20) default null comment '字典项代码'")
    private String optionCode;

    /**
     * 选线值
     */
    @Column(columnDefinition = "varchar(20) default null comment '字典项值'")
    private String optionValue;

    /**
     * 字典ID
     */
    @Column(columnDefinition = "bigint default null comment '字典ID'")
    private Long dictId;
}
