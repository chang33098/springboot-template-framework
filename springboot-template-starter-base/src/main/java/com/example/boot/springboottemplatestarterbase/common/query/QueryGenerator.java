package com.example.boot.springboottemplatestarterbase.common.query;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.springboottemplatestarterbase.base.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>mybatis-plus查询条件构造器</p>
 *
 * @author chang_
 * @description mybatis-plus查询条件构造器
 */
@Slf4j
public class QueryGenerator {

    //方案思路如下：
    //1.定义一个初始化方法，传入[泛型]和[查询对象的实例]，方法的返回值为mybatis-plus的Wrapper对象
    //2.定义相应的注解类，注解类中定义相应的字段匹配方式(例如：like，eq，大于，大于等于等)
    //3.定义一个用户存储字段key，value和注解属性的类，在对[查询对象]进行编列后，返回该对象的List
    //4.对于未使用的注解定义的查询条件，根据字段类型自动匹配其查询条件

    /**
     * 构建mybatis-plus的QueryWrapper对象
     * (查询对象以及返回的数据对象为同一个)
     *
     * @param queryPLO 参数对象
     * @param <T>      持久层实体类
     * @return QueryWrapper
     * @throws IllegalArgumentException
     */
    public static <T extends BaseEntity> QueryWrapper<T> generateQueryWrapper(T queryPLO, Class<?> clazz) throws IllegalAccessException {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>(); //初始化wrapper

        //1.获取查询参数类中的参数属性 & 初始化QueryList
        //

        Field[] fields = ReflectUtil.getFields(clazz);
        List<QueryField> queryFieldList = new ArrayList<>();

        //2.遍历Field List获取对应的字段属性
        //
        final String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";
        final QueryMatchType defaultMatchType = QueryMatchType.DEFAULT;

        for (Field field : fields) {
            if (Modifier.isFinal(field.getModifiers()) && Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            field.setAccessible(true); //开放访问权限

            final String fieldName = field.getName();
            final Object fieldValue = field.get(queryPLO);
            final Class<?> fieldTyp = field.getType();

            QueryField queryField = new QueryField().setFieldName(fieldName)
                    .setFieldValue(fieldValue)
                    .setFieldType(fieldTyp)
                    .setDateFormat(defaultDateFormat)
                    .setMatchType(defaultMatchType);

            QueryColumn queryColumn = field.getAnnotation(QueryColumn.class);
            if (ObjectUtil.isNotNull(queryColumn)) {
                queryField.setTableAlias(queryColumn.tableAlias());
                queryField.setMapperColumn(queryColumn.mapperColumn());
                queryField.setDateFormat(queryColumn.dateFormat());
                queryField.setMatchType(queryColumn.matchType());
            }
            queryFieldList.add(queryField);
        }

        //3.遍历queryFieldList对象，组装查询条件
        //
        installQueryCondition(queryWrapper, queryFieldList);

        return queryWrapper;
    }

    /**
     * 组装查询条件
     *
     * @param queryWrapper   MP QueryWrapper对象
     * @param queryFieldList 参数字段的属性值
     * @param <T>            持久层实体类
     */
    public static <T> void installQueryCondition(QueryWrapper<T> queryWrapper, List<QueryField> queryFieldList) {
        queryFieldList.forEach(queryField -> {
            //过滤值为空的字段
            //
            if (ObjectUtil.isNotNull(queryField.getFieldValue())) {
                final QueryMatchType matchType = queryField.getMatchType();
                if (matchType.equals(QueryMatchType.DEFAULT)) {
                    defaultQueryCondition(queryWrapper, queryField);
                } else {
                    matchTypeQueryCondition(queryWrapper, queryField);
                }
            }
        });
    }

    /**
     * 执行默认的匹配方式(根据字段类型自动选择匹配方式)
     * <p>
     * match rule
     * 布尔类型: true = 1, false = 0
     * 整数型: 统一使用eq
     * char: 采用eq的策略
     * 小数型: 统一采用le(小于等于)
     * 日期类型: 统一采用le(小于等于)
     *
     * @param queryWrapper QueryWrapper
     * @param queryField   查询字段
     * @param <T>          持久层实体类
     */
    private static <T> void defaultQueryCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {
        String fieldName = StrUtil.toUnderlineCase(queryField.getFieldName());
        if (StrUtil.isNotBlank(queryField.getTableAlias())) {
            fieldName = queryField.getTableAlias() + StrUtil.DOT + fieldName;
        }
        final Object fieldValue = queryField.getFieldValue();

        log.info("********* execute defaultQueryCondition *********");
        log.info("tableAlias:   {}", queryField.getTableAlias());
        log.info("fieldName:    {}", queryField.getFieldName());
        log.info("fieldValue:   {}", queryField.getFieldValue());
        log.info("fieldType:    {}", queryField.getFieldType().getName());
        log.info("mapperColumn: {}", queryField.getMapperColumn());
        log.info("dateFormat:   {}", queryField.getDateFormat());
        log.info("matchType:    {}", queryField.getMatchType());

        switch (queryField.getFieldType().getName()) {
            case "java.sql.Boolean":
                queryWrapper.eq(fieldName, ((Boolean) fieldValue) ? 1 : 0);
                break;
            case "java.lang.Character":
            case "java.lang.Byte":
            case "java.lang.Short":
            case "java.lang.Integer":
            case "java.lang.Long":
                queryWrapper.eq(fieldName, fieldValue);
                break;
            case "java.lang.Float":
            case "java.lang.Double":
            case "java.math.BigDecimal":
                queryWrapper.le(fieldName, fieldValue);
                break;
            case "java.util.Date":
            case "java.sql.Timestamp":
                break;
            case "java.lang.String":
                if (StrUtil.isNotBlank((CharSequence) fieldValue)) {
                    queryWrapper.like(fieldName, fieldValue);
                }
                break;
            default:
                throw new IllegalArgumentException("目前暂不支持[" + queryField.getFieldType().getName() + "]类型的查询条件，敬请谅解");
        }
    }

    /**
     * 执行@MatchType注解的查询方式
     *
     * @param queryWrapper QueryWrapper
     * @param queryField   查询字段
     * @param <T>          持久层实体类
     */
    private static <T> void matchTypeQueryCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {

    }
}
