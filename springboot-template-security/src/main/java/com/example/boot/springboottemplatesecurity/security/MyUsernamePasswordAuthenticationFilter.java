package com.example.boot.springboottemplatesecurity.security;

import com.example.boot.springboottemplatesecurity.properties.CustomSecurityConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: spring security的登录校验组件, 该组建的作用是对用户的身份进行验证
 * effect: 调用登录接口时会进入到此类的attemptAuthentication方法 进行相关校验操作
 * author: Chang__
 * createtime: 2018/10/29
 */
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    new AntPathRequestMatcher(filterProcessesUrl)

//    protected AbstractAuthenticationProcessingFilter(String defaultFilterProcessesUrl) {
//    setFilterProcessesUrl
//        this.setFilterProcessesUrl(defaultFilterProcessesUrl);
//    }

    public MyUsernamePasswordAuthenticationFilter(CustomSecurityConfiguration securityConfiguration, AuthenticationManager authenticationManager) {
        this.setUsernameParameter(securityConfiguration.getFormLogin().getUsernameParam());
        this.setPasswordParameter(securityConfiguration.getFormLogin().getPasswordParam());
//        this.setFilterProcessesUrl("/login"); //配置loginProcessingUrl, 即拦截的URL. 若不配置, 则默认执行父类中的attemptAuthentication()
        this.setAuthenticationManager(authenticationManager); //配置AuthenticationManager
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // TODO: 2019/9/30 验证码后期添加

        if (request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        request.getParameterMap().forEach((key, value) -> log.info("key: {}, value: {}", key, value));

        String username = obtainUsername(request); //获取登录用户名
        String password = obtainPassword(request); //获取登录密码

        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String BCryptpassword = passwordEncoder.encode(password); //加密密码

        username = username.trim();
        // 在这里处理密码或者用户名
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return super.attemptAuthentication(request, response);
    }
}
