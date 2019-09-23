package com.example.boot.configuration;

import cn.hutool.core.lang.Dict;
import com.example.boot.properties.CustomResourceConfiguration;
import com.example.boot.properties.CustomViewConfiguration;
import com.example.boot.properties.CustomUploadConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
        CustomViewConfiguration.class,
        CustomResourceConfiguration.class,
        CustomUploadConfiguration.class
})
public class WebMvcConfig implements WebMvcConfigurer {

    private final CustomViewConfiguration viewConfiguration;
    private final CustomResourceConfiguration resourceConfiguration;
    private final CustomUploadConfiguration uploadConfiguration;

    @Autowired
    public WebMvcConfig(CustomViewConfiguration viewConfiguration,
                        CustomResourceConfiguration resourceConfiguration,
                        CustomUploadConfiguration uploadConfiguration) {
        this.viewConfiguration = viewConfiguration;
        this.resourceConfiguration = resourceConfiguration;
        this.uploadConfiguration = uploadConfiguration;
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

    @Resource
    public void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
        Map<String, Object> domain = Dict.create()
                .set("IMAGE_DOMAIN", uploadConfiguration.getImage().getDomain());

        viewResolver.setStaticVariables(domain);
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
}
