package com.example.boot.springboottemplatebase.query;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>查询字段的各项属性值</p>
 *
 * @author chang_
 * @description 查询字段的属性(属性名称, 属性类型以及字段注解的属性)
 */
@Data
@Accessors(chain = true)
public class QueryField {

    /**
     * 数据表别名(出现多表关联时, 需要指定查询条件的所属表别名)
     */
    private String tableAlias;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 字段值
     */
    private Object fieldValue;

    /**
     * 字段类型
     */
    private Class<?> fieldType;

    /**
     * 映射的字段
     */
    private String mapperColumn;

    /**
     * 日期字段的时间格式化
     */
    private String dateFormat;

    /**
     * 匹配类型
     */
    private QueryMatchType matchType;

}
