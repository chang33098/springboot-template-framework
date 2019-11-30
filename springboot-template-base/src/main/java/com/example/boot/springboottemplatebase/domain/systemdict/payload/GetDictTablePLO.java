package com.example.boot.springboottemplatebase.domain.systemdict.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetDictTablePLO extends AbstractPaginationPLO {

    private String dictCode;
    private String dictName;
}
