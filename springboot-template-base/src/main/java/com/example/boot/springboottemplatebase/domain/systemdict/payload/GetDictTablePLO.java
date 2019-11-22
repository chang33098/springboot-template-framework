package com.example.boot.springboottemplatebase.domain.systemdict.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/4 22:10
 */
@Data
public class GetDictTablePLO extends AbstractPaginationPLO {

    private String dictCode;
    private String name;
}
