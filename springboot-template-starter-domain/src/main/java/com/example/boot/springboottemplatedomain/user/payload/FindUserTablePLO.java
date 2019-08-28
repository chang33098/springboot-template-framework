package com.example.boot.springboottemplatedomain.user.payload;

import com.example.boot.springboottemplatedomain.common.payload.AbstractPaginationPLO;
import lombok.Data;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/28 7:07
 */
@Data
public class FindUserTablePLO extends AbstractPaginationPLO {

    private Integer roleId;
    private String username;
    private String nickname;
    private String phone;
    private Integer status;
}
