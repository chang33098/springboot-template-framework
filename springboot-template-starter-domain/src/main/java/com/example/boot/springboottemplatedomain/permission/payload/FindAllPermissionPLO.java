package com.example.boot.springboottemplatedomain.permission.payload;

import com.example.boot.springboottemplatedomain.common.PaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:26
 */
@Data
public class FindAllPermissionPLO extends PaginationPLO {

    private String name;
    private String code;
}
