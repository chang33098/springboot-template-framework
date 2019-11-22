package com.example.boot.springboottemplatebase.domain.systemrole.payload;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

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
    @Length(max = 50)
    private String icon;
    @NotBlank
    @Length(max = 50)
    private String menuName;
    @NotNull
    private Integer menuLevel;
    @Range(min = 0, max = 127)
    private Integer sortNo;
    @NotNull
    private Long parentId;
    @Size(min = 1)
    private List<Long> permissionIds = new ArrayList<>();
}
