package com.example.boot.springboottemplatedomain.role.payload;

import com.example.boot.springboottemplatedomain.common.PaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/17 17:35
 */
@Data
public class FindAllRolePLO extends PaginationPLO {

    private String name;
}
