package com.example.boot.springboottemplatebase.query;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.boot.springboottemplatebase.base.entity.BaseEntity;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
     * @throws IllegalAccessException
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
    private static <T> void installQueryCondition(QueryWrapper<T> queryWrapper, List<QueryField> queryFieldList) {
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
        final String fieldName = executeWholeFieldName(queryField.getTableAlias(), queryField.getFieldName());
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
                throw new IllegalArgumentException(StrUtil.format("目前暂不支持[{}]类型的查询条件，敬请谅解", queryField.getFieldType().getName()));
        }
    }

    /**
     * 执行@MatchType注解的查询方式
     *
     * @param queryWrapper QueryWrapper
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void matchTypeQueryCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {
        final String wholeFieldName = executeWholeFieldName(queryField.getTableAlias(), queryField.getFieldName());
        final Object fieldValue = queryField.getFieldValue();

        final QueryMatchType matchType = queryField.getMatchType();

        log.info("********* execute matchTypeQueryCondition *********");
        log.info("tableAlias:   {}", queryField.getTableAlias());
        log.info("fieldName:    {}", queryField.getFieldName());
        log.info("fieldValue:   {}", queryField.getFieldValue());
        log.info("fieldType:    {}", queryField.getFieldType().getName());
        log.info("mapperColumn: {}", queryField.getMapperColumn());
        log.info("dateFormat:   {}", queryField.getDateFormat());
        log.info("matchType:    {}", queryField.getMatchType());

        //1.获取QueryField对象中的matchType属性
        //2.根据获取到的QueryMatchType对查询条件进行设置
        //3.且对注解的字段类型进行判别。例如: 当前的字段类型为字符串，则无法使用lt、le等注解

        switch (matchType) {
            case EQ:
                queryWrapper.eq(wholeFieldName, fieldValue);
                break;
            case NE:
                queryWrapper.ne(wholeFieldName, fieldValue);
                break;
            case GT:
                executeGtCondition(queryWrapper, wholeFieldName, queryField);
                break;
            case GE:
                executeGeCondition(queryWrapper, wholeFieldName, queryField);
                break;
            case IN:
                executeInCondition(queryWrapper, wholeFieldName, queryField);
                break;
            case NOT_IN:
                executeNotInCondition(queryWrapper, wholeFieldName, queryField);
            default:
                throw new IllegalArgumentException(StrUtil.format("目前暂时不支持[{}]的匹配方式，敬请谅解", matchType.getDescription()));
        }

    }

    /**
     * 执行GT(大于 >)的匹配方式
     * <p>
     * tips: 只允许数字类型(整数，小数)，日期类型和值为数字的字符串
     *
     * @param queryWrapper   QueryWrapper
     * @param wholeFieldName 完整的查询字段名(alias.filed_name)
     * @param queryField     查询字段
     * @param <T>            参数实体类
     */
    private static <T> void executeGtCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {
        final Object fieldValue = queryField.getFieldValue();

        if (fieldValue instanceof Short) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Integer) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Long) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof BigInteger) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Float) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Double) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof BigDecimal) {
            queryWrapper.gt(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Date) {
            final String dateFormat = queryField.getDateFormat();
            final String dateStr = DateUtil.format((Date) fieldValue, dateFormat);
            queryWrapper.gt(wholeFieldName, DateUtil.parse(dateStr, dateFormat));
        } else if (fieldValue instanceof String && NumberUtil.isNumber((String) fieldValue)) {
            queryWrapper.gt(wholeFieldName, queryField);
        } else {
            throw new IllegalArgumentException(StrUtil.format("错误的参数类型: {}  executeGtCondition方法只允许传入数字类型(整数，小数)，日期类型和值为数字的字符串类型的参数", fieldValue.getClass().getName()));
        }
    }

    /**
     * 执行GE(大于等于 >=)的匹配方式
     * <p>
     * tips: 只允许数字类型(整形，浮点型)，日期类型和值为数字的字符串
     *
     * @param queryWrapper   QueryWrapper
     * @param wholeFieldName 完整的查询字段名(alias.filed_name)
     * @param queryField     请求参数值
     * @param <T>            参数实体类
     */
    private static <T> void executeGeCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {
        final Object fieldValue = queryField.getFieldValue();

        if (fieldValue instanceof Short) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Integer) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Long) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof BigInteger) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Float) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Double) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof BigDecimal) {
            queryWrapper.ge(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Date) {
            final String dateFormat = queryField.getDateFormat();
            final String dateStr = DateUtil.format((Date) fieldValue, dateFormat);
            queryWrapper.ge(wholeFieldName, DateUtil.parse(dateStr, dateFormat));
        } else if (fieldValue instanceof String && NumberUtil.isNumber((String) fieldValue)) {
            queryWrapper.ge(wholeFieldName, queryField);
        } else {
            throw new IllegalArgumentException(StrUtil.format("错误的参数类型: {}  executeGeCondition方法只允许传入数字类型(整数，小数)，日期类型和值为数字的字符串类型的参数", fieldValue.getClass().getName()));
        }
    }

    /**
     * 执行LE(小于 <)的匹配方式
     * <p>
     * tips: 只允许数字类型(整形，浮点型)，日期类型和值为数字的字符串
     *
     * @param queryWrapper QueryWrapper
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void executeLtCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {

    }

    /**
     * 执行LE(小于等于 <=)的匹配条件
     * <p>
     * tips: 只允许数字类型(整形，浮点型)，日期类型和值为数字的字符串
     *
     * @param queryWrapper QueryWrapper
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void executeLeCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {

    }

    /**
     * 执行BETWEEN的匹配条件
     * <p>
     * tips: 只允许数字类型(整形，浮点型)、日期类型和值为字符串
     *
     * @param queryWrapper QueryWrapper对象
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void executeBetweenCondition(QueryWrapper<T> queryWrapper, QueryField queryField) {

    }

    /**
     * 执行NOT_BETWEEN的匹配条件
     * <p>
     * tips: 只允许数字类型(整形，浮点型)、日期类型和值为字符串
     *
     * @param queryWrapper QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名称
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void executeNotBetweenCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {

    }

    /**
     * 执行LIKE的匹配条件
     * <p>
     * tips: LIKE匹配无字段类型的显示(将传入的参数值统一转换成字符串)
     *
     * @param queryWrapper QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名称
     * @param queryField   查询字段
     * @param <T>          参数实体类型
     */
    private static <T> void executeLikeCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {


    }

    /**
     * 执行NOT LIKE的匹配条件
     *
     * @param queryWrapper QueryWrapper
     * @param wholeFieldName 完整的查询字段名称
     * @param queryField   查询字段
     * @param <T>          参数实体类
     */
    private static <T> void executeNotLikeCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {


    }

    /**
     * 执行LIKE LEFT的匹配条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名称
     * @param queryField   查询字段
     * @param <T>          参数实体类型
     */
    private static <T> void executeLikeLeftCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {

    }

    /**
     * 执行LIKE RIGHT的匹配条件
     *
     * @param queryWrapper QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名称
     * @param queryField   查询字段
     * @param <T>          参数实体类型
     */
    private static <T> void executeLikeRightCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {

    }

    /**
     * 执行IN的匹配条件
     * <p>
     * tips: 该方法需显示字段类型，只允许传字符串(以某种形式分割 例如: 逗号、分割线等元素)、数组、List、Set类型的参数
     *
     * @param queryWrapper   QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名(alias.filed_name)
     * @param queryField     查询字段对象
     * @param <T>            参数实体类型
     */
    private static <T> void executeInCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {
        final Object fieldValue = queryField.getFieldValue();

        if (fieldValue instanceof List) {
            queryWrapper.in(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Set) {
            queryWrapper.in(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof String) {
            String[] data = StrUtil.split((CharSequence) fieldValue, StrUtil.COMMA); //通过逗号对数组进行分割
            queryWrapper.in(wholeFieldName, (Object) data);
        } else {
            throw new IllegalArgumentException(StrUtil.format("错误的参数类型: {}  executeInCondition方法只允许传入[字符串]、[数组]、[List]和[Set]类型的参数", fieldValue.getClass().getName()));
        }
    }

    /**
     * 执行NOT IN的匹配条件
     * <p>
     * tips: 该方法需显示字段类型，只允许传字符串(以某种形式分割 例如: 逗号、分割线等元素)、数组、List、Set类型的参数
     *
     * @param queryWrapper   QueryWrapper对象
     * @param wholeFieldName 完整的查询字段名(alias.filed_name)
     * @param queryField     查询字段对象
     * @param <T>            参数实体类
     */
    private static <T> void executeNotInCondition(QueryWrapper<T> queryWrapper, final String wholeFieldName, final QueryField queryField) {
        final Object fieldValue = queryField.getFieldValue();

        if (fieldValue instanceof List) {
            queryWrapper.notIn(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof Set) {
            queryWrapper.notIn(wholeFieldName, fieldValue);
        } else if (fieldValue instanceof String) {
            String[] data = StrUtil.split((CharSequence) fieldValue, StrUtil.COMMA); //通过逗号对数组进行分割
            queryWrapper.notIn(wholeFieldName, (Object) data);
        } else {
            throw new IllegalArgumentException(StrUtil.format("错误的参数类型: {}  executeNotInCondition方法只允许传入[字符串]、[数组]、[List]和[Set]类型的参数", fieldValue.getClass().getName()));
        }
    }

    /**
     * 打包完成的字段名称(数据表别名.字段名称)
     *
     * @param tableAlias 数据表别名
     * @param fieldName  数据库字段名称
     * @return 完成的字段名称
     */
    private static String executeWholeFieldName(String tableAlias, String fieldName) {
        String wholeFieldName = fieldName;
        if (StrUtil.isNotBlank(tableAlias)) {
            wholeFieldName = tableAlias + StrUtil.DOT + fieldName;
        }
        return wholeFieldName;
    }
}
