package com.example.boot.model.permission.payload;

import com.example.boot.model.common.payload.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:26
 */
@Data
public class GetPermissionTablePLO extends AbstractPaginationPLO {

    private String name;
    private String code;
}
