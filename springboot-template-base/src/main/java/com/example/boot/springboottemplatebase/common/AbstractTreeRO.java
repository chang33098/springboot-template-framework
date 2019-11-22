package com.example.boot.springboottemplatebase.common;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * LayUI tree组件的相应数据
 *
 * @author Chang
 * @date 2019/8/19 23:22
 */
@Data
public abstract class AbstractTreeRO<T> {

    private Long id;
    private String title;
    private Boolean spread = true;
    private List<AbstractTreeRO> children = new ArrayList<>();

    public abstract void transferTree(T data);
}
