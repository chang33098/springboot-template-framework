package com.example.boot.springboottemplatesecurity.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: spring security的登录校验组件, 该组建的作用是对用户的身份进行验证
 * effect: 调用登录接口时会进入到此类的attemptAuthentication方法 进行相关校验操作
 * author: Chang__
 * createtime: 2018/10/29
 */
public class MyUsernamePasswordAuthenticationFilter {

}

//@SuppressWarnings("unused")
//public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    public  MyUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager,
//                                                   AuthenticationSuccessHandler successHandler,
//                                                   AuthenticationFailureHandler failureHandler){
//        this.setFilterProcessesUrl("/j_spring_secutity_check");  //这句代码很重要，设置登陆的url 要和 WebSecurityConfig 配置类中的.loginProcessingUrl("/auth/v1/api/login/entry") 一致，如果不配置则无法执行 重写的attemptAuthentication 方法里面而是执行了父类UsernamePasswordAuthenticationFilter的attemptAuthentication（）
//        this.setAuthenticationManager(authenticationManager);   // AuthenticationManager 是必须的
//        this.setAuthenticationSuccessHandler(successHandler);  //设置自定义登陆成功后的业务处理
//        this.setAuthenticationFailureHandler(failureHandler); //设置自定义登陆失败后的业务处理
//    }
//
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        // TODO: 2019/9/30 验证码后期添加
//
//        if (request.getMethod().equals("POST")) {
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        }
//        String username = obtainUsername(request); //获取登录用户名
//        String password = obtainPassword(request); //获取登录密码
//
//        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String BCryptpassword = passwordEncoder.encode(password); //
//
//        username = username.trim();
//        // 在这里处理密码或者用户名
//        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
//        // Allow subclasses to set the "details" property
//        setDetails(request, authRequest);
//
//        return super.attemptAuthentication(request, response);
//    }
//}
