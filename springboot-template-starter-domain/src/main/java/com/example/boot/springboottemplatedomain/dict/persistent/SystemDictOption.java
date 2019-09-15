package com.example.boot.springboottemplatedomain.dict.persistent;

import lombok.Data;

import javax.persistence.*;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/15 22:43
 */
@Data
public class SystemDictOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "tinyint comment '选项代码'")
    private Integer code;

    @Column(columnDefinition = "varchar(255) comment '选线值'")
    private String value;

    @ManyToOne
    @JoinColumn(name = "parent_id", columnDefinition = "bigint comment '字典ID'")
    private SystemDict parent;
}
