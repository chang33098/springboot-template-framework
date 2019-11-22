package com.example.boot.springboottemplatebase.domain.systempage.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:26
 */
@Data
public class GetPageTablePLO extends AbstractPaginationPLO {

    private String name;
}
