package com.example.boot.springboottemplatebase.base.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>分页数据基础类</p>
 *
 * @author Chang
 * @date 2019/7/29 23:25
 */
@Data
public abstract class AbstractPaginationPLO {

    /**
     * 页码
     */
    @NotNull(message = "Invalid parameter")
    @Min(value = 1)
    private Integer pageNo;
    /**
     * 页数
     */
    @NotNull(message = "Invalid parameter")
    @Range(min = 10, max = 100)
    private Integer pageSize;
}
