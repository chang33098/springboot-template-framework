package com.example.boot.springboottemplatesecurity.security;

import com.example.boot.springboottemplatebase.domain.systemrole.value.SecurityGetPagePermissionListVO;
import com.example.boot.springboottemplatebase.service.SystemPagePermissionRefService;
import com.example.boot.springboottemplatebase.service.SystemPageService;
import com.example.boot.springboottemplatebase.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description: 系统启动时, 配置各访问资源所需的权限信息(即url)
 * effect: 应用场景
 * author: Chang
 * createtime: 2018/10/27
 */
@Slf4j
@Component
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private SystemPagePermissionRefService pagePermissionRefService;
    @Autowired
    private SystemPageService pageService;
    @Autowired
    private SystemPermissionService permissionService;

//    @Autowired
//    public MyFilterSecurityMetadataSource(SystemPagePermissionRefService pagePermissionRefService, SystemPageService pageService, SystemPermissionService permissionService) {
//        this.pagePermissionRefService = pagePermissionRefService;
//        this.pageService = pageService;
//        this.permissionService = permissionService;
//    }

    @PostConstruct
    private void loadResources() {
        resourceMap = new ConcurrentHashMap<>();

        List<SecurityGetPagePermissionListVO> permissionRefs = pagePermissionRefService.securityGetPagePermissionList();
        if (permissionRefs.isEmpty()) return;

        permissionRefs.forEach(permissionRef -> {
            List<String> interceptUrls = Stream.of(permissionRef.getInterceptUrls().split(";")).collect(Collectors.toList());

            if (!interceptUrls.isEmpty()) {
                interceptUrls.forEach(url -> {
                    final String pageCode = permissionRef.getPageCode();
                    final String permissionCode = permissionRef.getPermissionCode();

                    List<ConfigAttribute> configs = Stream.of(this.getSecurityConfig(pageCode, permissionCode)).collect(Collectors.toList());
                    resourceMap.put(url, configs);
                });
            }
        });
    }

    /**
     * 通过页面代码和权限代码生成SecurityConfig配置
     *
     * @param pageCode       页面代码
     * @param permissionCode 权限代码
     * @return SecurityConfig
     */
    private SecurityConfig getSecurityConfig(final String pageCode,
                                             final String permissionCode) {
        return new SecurityConfig(pageCode + ":" + permissionCode);
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
