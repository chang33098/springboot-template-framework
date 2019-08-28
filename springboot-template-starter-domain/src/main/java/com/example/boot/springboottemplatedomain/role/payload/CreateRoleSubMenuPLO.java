package com.example.boot.springboottemplatedomain.role.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/20 0:06
 */
@Data
public class CreateRoleSubMenuPLO {

    @NotNull
    private Long pageId;
    private String icon;
    @NotBlank
    private String menuName;
    @NotNull
    private Integer menuLevel;
    private Integer sortNo = 0;
    private Long parentId;
    @Size(min = 1)
    private List<Long> permissionIds = new ArrayList<>();
}
