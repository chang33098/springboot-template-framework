package com.example.boot.springboottemplatedomain.common;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:25
 */
@Data
public class PaginationPLO {

    @NotNull(message = "Invalid parameter")
    @Min(value = 1)
    private Integer pageNo;

    @NotNull(message = "Invalid parameter")
    @Range(min = 10, max = 100)
    private Integer limit;
}
