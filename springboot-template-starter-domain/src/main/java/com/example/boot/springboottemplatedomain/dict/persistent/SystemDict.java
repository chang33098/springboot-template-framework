package com.example.boot.springboottemplatedomain.dict.persistent;

import lombok.Data;
import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 0:13
 */
@Data
@Entity
@Table(name = "system_dict", indexes = {
        @Index(name = "type_index", columnList = "type", unique = true)
})
public class SystemDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "tinyint comment '字典级别(1:父节点, 2:子节点)'")
    private Integer dictLevel;

    @Column(columnDefinition = "varchar(50) comment '字典类型(由大写英文和下划线组成)'")
    private String type;

    @Column(columnDefinition = "varchar(100) comment '字典类型名称'")
    private String name;

    @Column(columnDefinition = "tinyint comment '字典代码(采用数字表示)'")
    private Integer code;

    @Column(columnDefinition = "varchar(50) comment '字典值'")
    private String value;

    @Column(columnDefinition = "varchar(255) comment '字典描述'")
    private String description;

    @Column(columnDefinition = "tinyint default '0' comment '删除标记(0:未删除, 1:已删除)'")
    private Boolean deleted;

    @ManyToOne
    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '父类ID'")
    private SystemDict parent;
}
