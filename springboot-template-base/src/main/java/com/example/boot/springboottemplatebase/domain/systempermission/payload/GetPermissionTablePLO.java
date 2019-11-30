package com.example.boot.springboottemplatebase.domain.systempermission.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/7/29 23:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetPermissionTablePLO extends AbstractPaginationPLO {

    private String permissionCode;
    private String permissionName;
}
