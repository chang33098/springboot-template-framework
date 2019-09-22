package com.example.boot.springboottemplatedomain.role.response;

import com.example.boot.springboottemplatedomain.common.response.AbstractTreeRO;
import com.example.boot.springboottemplatedomain.role.constants.MenuLevel;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/19 23:34
 */
@Data
public class GetRoleMenuTreeRO extends AbstractTreeRO<RoleMenuRef> {

    private Long pageId;
    private String icon;
    private Integer menuLevel;
    private Integer sortNo;

    @Override
    public void transferTree(RoleMenuRef data) {
        this.setId(data.getId());
        this.setTitle(data.getMenuName());

        if (Objects.equals(data.getMenuLevel(), MenuLevel.CHILD_MENU.getType()))
            this.setPageId(data.getPage().getId());

        this.setIcon(data.getIcon());
        this.setMenuLevel(data.getMenuLevel());
        this.setSortNo(data.getSortNo());

        List<AbstractTreeRO> children = data.getChildren().stream().map(child -> {
            GetRoleMenuTreeRO menuRO = new GetRoleMenuTreeRO();
            menuRO.transferTree(child);
            return menuRO;
        }).collect(Collectors.toList());

        this.setChildren(children);
    }
}
