package com.example.boot.springboottemplatebase.domain.systemuser.payload;

import com.example.boot.springboottemplatebase.common.AbstractPaginationPLO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 7:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FindUserTablePLO extends AbstractPaginationPLO {

    private Integer roleId;
    private String username;
    private String nickname;
    private String phone;
    private Integer status;
}
