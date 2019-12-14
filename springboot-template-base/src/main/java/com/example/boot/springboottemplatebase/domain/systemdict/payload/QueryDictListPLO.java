package com.example.boot.springboottemplatebase.domain.systemdict.payload;

import com.example.boot.springboottemplatebase.query.QueryColumn;
import com.example.boot.springboottemplatebase.query.QueryMatchType;
import lombok.Data;

/**
 * @author chang_
 * @description 数据字典列表查询PLO
 */
@Data
public class QueryDictListPLO {

    /**
     * 字典代码
     */
    @QueryColumn(matchType = QueryMatchType.LIKE)
    private String dictCode;

    /**
     * 字典名称
     */
    @QueryColumn(matchType = QueryMatchType.LIKE)
    private String dictName;
}
