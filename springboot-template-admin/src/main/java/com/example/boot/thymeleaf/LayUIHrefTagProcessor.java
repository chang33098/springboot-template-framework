package com.example.boot.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IModel;
import org.thymeleaf.model.IModelFactory;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.AbstractElementTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Thymeleaf自定义标签th-layui:href
 * <p>
 * thymeleaf official doc: https://www.thymeleaf.org/doc/tutorials/3.0/extendingthymeleaf.html#changing-the-css-class-by-team-position
 *
 * @author Chang
 * @date 2019/10/20 23:33
 */
@Slf4j
public class LayUIHrefTagProcessor extends AbstractAttributeTagProcessor {

    private static final String ATTRIBUTE = "href";
    private static final int PRECEDENCE = 10000;

    /**
     * templateMode: 模板模式，这里使用HTML模板。
     * dialectPrefix: 标签前缀。即xxx:text中的xxx。在此例子中prefix为[th-layui:href]。
     * elementName：匹配标签元素名。举例来说如果是div，则我们的自定义标签只能用在div标签中。为null能够匹配所有的标签。
     * prefixElementName: 标签名是否要求前缀。
     * attributeName: 自定义标签属性名。这里为text。
     * prefixAttributeName：属性名是否要求前缀，如果为true，Thymeeleaf会要求使用text属性时必须加上前缀，即thSys:text。
     * precedence：标签处理的优先级，此处使用和Thymeleaf标准方言相同的优先级。
     * removeAttribute：标签处理后是否移除自定义属性。
     *
     * @param dialectPrefix 自定义标签的前缀
     */
    protected LayUIHrefTagProcessor(String dialectPrefix) {
        super(TemplateMode.HTML, dialectPrefix, null, false,
                ATTRIBUTE, true, PRECEDENCE, true);
    }

    /**
     * 自定义标签处理
     *
     * @param templateContext  thymeleaf模板上下文(参数包含执行模板的上下文：变量，模板数据等)
     * @param elementTag       元素标签处理器(参数包含执行模板的上下文：变量，模板数据等。)
     * @param attributeName    元素名称(属性名称ATTRIBUTE)
     * @param attributeValue   元素值(属性值)
     * @param structureHandler 结构处理器(它允许处理器向引擎提供有关由于处理器执行而应执行的操作的指令)
     */
    @Override
    protected void doProcess(ITemplateContext templateContext, IProcessableElementTag elementTag,
                             AttributeName attributeName, String attributeValue,
                             IElementTagStructureHandler structureHandler) {
        final IEngineConfiguration configuration = templateContext.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(templateContext, attributeValue);
        final String href = (String) expression.execute(templateContext);
        structureHandler.replaceAttribute(attributeName, "lay-href", href);
    }
}
