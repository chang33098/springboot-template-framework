package com.example.boot.springboottemplatestarter.security;

import com.example.boot.springboottemplatestarter.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * description: 系统启动时, 配置各访问资源所需的权限信息(即url)
 * effect: 应用场景
 * author: Chang
 * createtime: 2018/10/27
 */
@Component
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    private final PermissionService permissionService;

    @Autowired
    public MyFilterSecurityMetadataSource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    // TODO: 2019/8/15 fix it!!!

    private void loadResources() {
        resourceMap = new ConcurrentHashMap<>();

//        List<SystemPermissionUrl> permissionUrls = permissionService.securityGetAllPermissionUrl();
//        if (!permissionUrls.isEmpty()) {
//
//            permissionUrls.forEach(permissionUrl -> {
//                List<ConfigAttribute> configs = (List<ConfigAttribute>) Stream.builder().add(new SecurityConfig(
//                        permissionUrl.getPermission().getPage().getCode() +
//                        "_" +
//                        permissionUrl.getPermission().getCode()));
//                resourceMap.put(permissionUrl.getMatchUrl(), configs);
//            });
//        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        /**
         * 加载资源——权限的关联信息, 初始化resourceMap集合
         */
        if (resourceMap == null) {
            loadResources();
        }

        /**
         * 判断请求的Url是否包含在resourceMap中
         */
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;

        for (String resourceUrl : resourceMap.keySet()) {
            matcher = new AntPathRequestMatcher(resourceUrl);
            if (matcher.matches(request)) {
                return resourceMap.get(resourceUrl);
            }
        }

        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
