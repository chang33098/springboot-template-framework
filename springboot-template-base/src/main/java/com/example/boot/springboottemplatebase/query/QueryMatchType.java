package com.example.boot.springboottemplatebase.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>查询条件的匹配类型</p>
 *
 * @author chang_
 * @description 查询条件的匹配类型
 */
@Getter
@AllArgsConstructor
public enum QueryMatchType {

    DEFAULT("根据字段类型进行自动匹配"),

    EQ("等于 = "), NE("不等于 <> "), GT("大于 > "), GE("大于等于 >= "),

    LT("小于 < "), LE("小于等于 <= "),

    LIKE("LIKE '%值%' "), NOT_LIKE("NOT LIKE '%值%' "), LIKE_LEFT("LIKE '%值'"), LIKE_RIGHT("LIKE '值%' "),

    IS_NULL("字段 IS NULL "), IS_NOT_NULL("字段 IS NOT NULL "),

    IN("字段 IN (value.get(0), value.get(1), ...) "), NOT_IN("字段 IN (value.get(0), value.get(1), ...) ");

    private String description;

}
