package com.example.boot.configuration;

import cn.hutool.core.lang.Dict;
import com.example.boot.properties.CustomResourceConfiguration;
import com.example.boot.properties.CustomViewConfiguration;
import com.example.boot.properties.CustomUploadConfiguration;
import com.example.boot.thymeleaf.LayUITagDialect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by chang .
 * Date: 2019/2/8
 * Time: 18:27
 * Description: To change this template use File | Settings | File Templates.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        ThymeleafProperties.class,
        CustomViewConfiguration.class,
        CustomResourceConfiguration.class,
        CustomUploadConfiguration.class
})
public class WebMvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {

    private final ThymeleafProperties thymeleafProperties;
    private final CustomViewConfiguration viewConfiguration;
    private final CustomResourceConfiguration resourceConfiguration;
    private final CustomUploadConfiguration uploadConfiguration;

    private ApplicationContext applicationContext;

    @Autowired
    public WebMvcConfiguration(ThymeleafProperties thymeleafProperties,
                               CustomViewConfiguration viewConfiguration,
                               CustomResourceConfiguration resourceConfiguration,
                               CustomUploadConfiguration uploadConfiguration) {
        this.thymeleafProperties = thymeleafProperties;
        this.viewConfiguration = viewConfiguration;
        this.resourceConfiguration = resourceConfiguration;
        this.uploadConfiguration = uploadConfiguration;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        viewConfiguration.getCommonViews().forEach(view -> registry.addViewController(view.getPath()).setViewName(view.getName()));
        viewConfiguration.getBizViews().forEach(view -> registry.addViewController(view.getPath()).setViewName(view.getName()));
        viewConfiguration.getErrorViews().forEach(view -> registry.addViewController(view.getPath()).setViewName(view.getName()));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        resourceConfiguration.getResources().forEach(
                resourceHandler ->
                        registry.addResourceHandler(resourceHandler.getPathPattern()).addResourceLocations(resourceHandler.getLocation())
        );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(3600);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
            factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            factory.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            factory.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405"));
            factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        };
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCharacterEncoding(thymeleafProperties.getEncoding().toString());
        templateResolver.setPrefix(thymeleafProperties.getPrefix());
        templateResolver.setSuffix(thymeleafProperties.getSuffix());
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(thymeleafProperties.getMode());
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(thymeleafProperties.isCache());
        templateResolver.setApplicationContext(this.applicationContext);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(new LayUITagDialect());
        templateEngine.addDialect(new SpringSecurityDialect());
        return templateEngine;
    }

    @Resource
    public void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        Map<String, Object> domain = Dict.create()
                .set("IMAGE_DOMAIN", uploadConfiguration.getImage().getDomain());
        viewResolver.setStaticVariables(domain);
    }
}
