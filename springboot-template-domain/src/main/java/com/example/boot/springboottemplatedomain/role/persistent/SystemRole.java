package com.example.boot.springboottemplatedomain.role.persistent;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * description write this class description...
 *
 * @author ANdady
 * @date 2019/7/21 20:12
 */
@Data
@Entity
@Table(name = "system_role")
public class SystemRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50) comment '角色名称'")
    private String name;

    @Column(columnDefinition = "varchar(500) comment '角色描述'")
    private String description;

    @Column(columnDefinition = "datetime comment '创建时间'")
    private Timestamp createTime;

    @Column(columnDefinition = "datetime comment '更新时间'")
    private Timestamp updateTime;
}
