package com.example.boot.springboottemplatesecurity.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * description: 权限决策处理器
 * effect: 判断当前用户是否有资格访问被请求的资源
 * author: Chang
 * createtime: 2018/10/27
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    /**
     * 判断当前用户是否拥有请求资源的访问权限
     * <p>
     * 权限的校验流程:
     * 当用户请求相对匹配资源时, 将登陆用户所拥有的权限与请求该资源所要求的权限
     * 进行匹配. 匹配通过, 则放行; 不通过, 则抛出AccessDeniedException的异常
     *
     * @param authentication 登录用户所拥有的权限列表
     * @param object         用户发起的request请求
     * @param attributes     访问所需的权限列表
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isEmpty(attributes)) {
            throw new AccessDeniedException("无访问权限.");
        }

        List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities(); //用户所拥有的权限
        for (ConfigAttribute attribute : attributes) {
            String requestPermission = attribute.getAttribute(); //获取请求所需的权限

            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(requestPermission)) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("无访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
