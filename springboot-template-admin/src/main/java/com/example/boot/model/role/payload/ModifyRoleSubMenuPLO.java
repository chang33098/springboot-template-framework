package com.example.boot.model.role.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
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
    @Length(max = 50)
    private String icon;
    @NotBlank
    @Length(max = 50)
    private String menuName;
    @Range(min = 0, max = 127)
    private Integer sortNo = 0;
    @Size(min = 1)
    private List<Long> permissionIds = new ArrayList<>();
}
