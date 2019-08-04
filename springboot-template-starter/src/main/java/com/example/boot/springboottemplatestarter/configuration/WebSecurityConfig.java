package com.example.boot.springboottemplatestarter.configuration;

import com.example.boot.springboottemplatestarter.properties.CustomSecurityConfiguration;
import com.example.boot.springboottemplatestarter.security.MyFilterSecurityInterceptor;
import com.example.boot.springboottemplatestarter.security.MyUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Chang on 2018/10/25.
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CustomSecurityConfiguration.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomSecurityConfiguration securityConfiguration;

    @Autowired
    public WebSecurityConfig(CustomSecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关闭csrf验证码

        http.authorizeRequests().anyRequest().authenticated(); //除permitAll之外的所有请求需登录过后才能访问

        http.formLogin().usernameParameter(securityConfiguration.getFormLogin().getUsernameParam())
                .passwordParameter(securityConfiguration.getFormLogin().getPasswordParam())
                .loginPage(securityConfiguration.getFormLogin().getLoginPage())
                .loginProcessingUrl(securityConfiguration.getFormLogin().getLoginProcessingUrl())
                .defaultSuccessUrl(securityConfiguration.getFormLogin().getDefaultSuccessUrl())
                .failureUrl(securityConfiguration.getFormLogin().getFailureUrl())
                .permitAll();

        http.logout().logoutUrl(securityConfiguration.getLogout().getLogoutUrl())
                .logoutSuccessUrl(securityConfiguration.getLogout().getLogoutSuccessUrl())
                .invalidateHttpSession(securityConfiguration.getLogout().getInvalidateSession());

        http.sessionManagement().maximumSessions(1); //同一帐号好的session只能存在一个

        http.rememberMe().rememberMeParameter(securityConfiguration.getRememberMe().getRememberMeParam())
                .tokenValiditySeconds(securityConfiguration.getRememberMe().getTokenValiditySeconds());

        http.headers().frameOptions().sameOrigin(); //在同一个domain下, 允许以iframe的方式打开页面
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity security) {
        securityConfiguration.getPermitMatchUrls().forEach(url -> security.ignoring().antMatchers(url));
    }

    @Bean
    public MyUserDetailService userDetailService() {
        return new MyUserDetailService();
    }

    @Bean
    public MyFilterSecurityInterceptor filterSecurityInterceptor() {
        return new MyFilterSecurityInterceptor();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
