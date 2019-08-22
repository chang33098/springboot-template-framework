package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDZ on 2019/8/22.
 */
@Data
public class ModifyRoleSubMenuPLO {

    @NotNull
    private Long pageId;
    private String icon;
    @NotNull
    private String menuName;
    private Integer sortNo = 0;
    @NotEmpty
    @Size(min = 1)
    private List<Long> permissionIds = new ArrayList<>();
}
