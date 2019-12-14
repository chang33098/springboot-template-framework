package com.example.boot.springboottemplatebase.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>查询字段</p>
 * <p>
 * tableAlias: 数据表别名
 * dateFormat: 日期的格式化(推荐使用DatePattern.class中的日期格式)
 * mapperColumn: 对应的数据库字段
 * matchType: 字段的匹配类型
 *
 * @author chang_
 * @description 查询字段注解
 */
@SuppressWarnings("unused")
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryColumn {

    String tableAlias() default "";

    String mapperColumn() default "";

    String dateFormat() default "";

    QueryMatchType matchType() default QueryMatchType.DEFAULT;
}
