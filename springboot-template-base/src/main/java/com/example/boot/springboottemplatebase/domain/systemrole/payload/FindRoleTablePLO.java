package com.example.boot.springboottemplatebase.domain.systemrole.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/17 17:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FindRoleTablePLO extends AbstractPaginationPLO {

    private String roleName;
}
