package com.example.boot.springboottemplatestarter.configuration;

import com.example.boot.springboottemplatestarter.properties.CustomResourceConfiguration;
import com.example.boot.springboottemplatestarter.properties.CustomViewConfiguration;
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

/**
 * Created by chang .
 * Date: 2019/2/8
 * Time: 18:27
 * Description: To change this template use File | Settings | File Templates.
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({CustomViewConfiguration.class, CustomResourceConfiguration.class})
public class WebMvcConfig implements WebMvcConfigurer {

    private final CustomViewConfiguration viewConfiguration;
    private final CustomResourceConfiguration resourceConfiguration;

    @Autowired
    public WebMvcConfig(CustomViewConfiguration viewConfiguration, CustomResourceConfiguration resourceConfiguration) {
        this.viewConfiguration = viewConfiguration;
        this.resourceConfiguration = resourceConfiguration;
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
//        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/"); //layuiadmin的样式以及插件
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(3600);
    }
}
