package com.example.boot.springboottemplatebase.base.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>查询对象的基础类</p>
 *
 * @author chang_
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BaseQueryPLO {

    private String orderBy;

    private String sortBy;
}
