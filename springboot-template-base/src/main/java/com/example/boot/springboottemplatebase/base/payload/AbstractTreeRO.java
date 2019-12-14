package com.example.boot.springboottemplatebase.base.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>LayUI tree组件的相应数据</p>
 *
 * @author Chang
 * @date 2019/8/19 23:22
 */
@Data
public abstract class AbstractTreeRO<T> {

    /**
     * 数据ID
     */
    private Long id;
    /**
     * 菜单标题
     */
    private String title;
    /**
     * 是否默认展开
     */
    private Boolean spread = true;
    /**
     * 子节点数据
     */
    private List<AbstractTreeRO> children = new ArrayList<>();

    /**
     * 抽象方法——将查询到的数据转化成树状图的数据结构
     *
     * @param data 传入的数据
     */
    public abstract void transferTree(T data);
}
