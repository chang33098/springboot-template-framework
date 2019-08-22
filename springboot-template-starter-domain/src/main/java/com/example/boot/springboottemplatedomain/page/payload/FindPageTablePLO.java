package com.example.boot.springboottemplatedomain.page.payload;

import com.example.boot.springboottemplatedomain.common.payload.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:26
 */
@Data
public class FindPageTablePLO extends AbstractPaginationPLO {

    private String name;
}
