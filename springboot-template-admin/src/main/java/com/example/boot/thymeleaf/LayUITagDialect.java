package com.example.boot.thymeleaf;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

/**
 * 定义thymeleaf-layui的方言(th-lay:xxx)
 *
 * @author Chang
 * @date 2019/10/21 22:19
 */
public class LayUITagDialect extends AbstractProcessorDialect {

    private static final String DIALECT_NAME = "LAYUI_DIALECT";
    private static final String DEFAULT_PREFIX = "layui";

    public LayUITagDialect() {
        // We will set this dialect the same "dialect processor" precedence as
        // the Standard Dialect, so that processor executions can interleave.
        super(DIALECT_NAME, DEFAULT_PREFIX, StandardDialect.PROCESSOR_PRECEDENCE);
    }

    /**
     * 这里将定义好的标签处理器进行统一处理
     * <p>
     * Processor List
     * 1.LayUIHrefTagProcessor：LayUI菜单组件的属性layui:href='xxx'
     * 2.LayUIDirectionProcessor: LayUI菜单组件的属性layui:direction='xxx'
     * 3.LayUITipsProcessor: LayUI菜单组件的属性layui:tips='xxx'
     *
     * @param dialectPrefix 方言前缀
     * @return Set<IProcessor>
     */
    @Override
    public Set<IProcessor> getProcessors(final String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new LayUIHrefTagProcessor(dialectPrefix));
        processors.add(new LayUIDirectionProcessor(dialectPrefix));
        processors.add(new LayUITipsProcessor(dialectPrefix));
        return processors;
    }
}
