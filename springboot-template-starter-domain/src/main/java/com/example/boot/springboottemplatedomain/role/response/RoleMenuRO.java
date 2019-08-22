package com.example.boot.springboottemplatedomain.role.response;

import com.example.boot.springboottemplatedomain.common.response.AbstractTreeNodeRO;
import com.example.boot.springboottemplatedomain.role.persistent.RoleMenuRef;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/19 23:34
 */
@Data
public class RoleMenuRO extends AbstractTreeNodeRO<RoleMenuRef> {

    private String icon;
    private Integer menuLevel;
    private Integer sortNo;

    @Override
    public void transferTreeNode(RoleMenuRef data) {
        this.setId(data.getId());
        this.setTitle(data.getMenuName());
        this.setIcon(data.getIcon());
        this.setMenuLevel(data.getMenuLevel());
        this.setSortNo(data.getSortNo());

        List<AbstractTreeNodeRO> children = data.getChildMenus().stream().map(childMenu -> {
            RoleMenuRO menuRO = new RoleMenuRO();
            menuRO.transferTreeNode(childMenu);
            return menuRO;
        }).collect(Collectors.toList());

        this.setChildren(children);
    }
}
