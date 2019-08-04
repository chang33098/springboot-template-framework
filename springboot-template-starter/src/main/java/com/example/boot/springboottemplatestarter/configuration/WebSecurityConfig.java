package com.example.boot.springboottemplatestarter.configuration;

import com.example.boot.springboottemplatestarter.security.MyFilterSecurityInterceptor;
import com.example.boot.springboottemplatestarter.security.MyUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Chang on 2018/10/25.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关闭csrf验证码
        http.authorizeRequests()
                .antMatchers("/static/layuiadmin/**", "/static/attachment/**").permitAll() //静态资源路径
                .antMatchers("/login/**", "/logout/**").permitAll() //登陆页面 & 注销页面
                .antMatchers("/error/**").permitAll() //异常页面
                .anyRequest().authenticated(); //除permitAll之外的所有请求需登录过后才能访问
        http.formLogin().usernameParameter("username").passwordParameter("password")
                .loginPage("/login") //登陆页面的访问Url
                .loginProcessingUrl("/login/submit") //登陆表单对应的url
                .defaultSuccessUrl("/index") //登陆成功后跳转的页面
                .failureUrl("/login")
                .permitAll();
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true); //注销后, 清除session
        http.sessionManagement().maximumSessions(1); //同一帐号好的session只能存在一个
        http.rememberMe()
                .rememberMeParameter("remember-me")
                .tokenValiditySeconds(3600);

        http.headers().frameOptions().sameOrigin(); //在同一个domain下, 允许以iframe的方式打开页面
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        // 配置指定用户权限信息  通常生产环境都是从数据库中读取用户权限信息而不是在这里配置
        //auth.inMemoryAuthentication().withUser("username1").password("123456").roles("USER").and().withUser("username2").password("123456").roles("USER","AMDIN");

        // ****************   基于数据库中的用户权限信息 进行认证
        //指定密码加密所使用的加密器为 bCryptPasswordEncoder()
        //需要将密码加密后写入数据库
        // myUserDetailService 类中获取了用户的用户名、密码以及是否启用的信息，查询用户所授予的权限，用来进行鉴权，查询用户作为群组成员所授予的权限
//        auth.userDetailsService(myUserDetailService).passwordEncoder(bCryptPasswordEncoder());
        //不删除凭据，以便记住用户
//        auth.eraseCredentials(false);

        auth.userDetailsService(userDetailService()).passwordEncoder(bCryptPasswordEncoder());
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

//    /**
//     * 验证登录验证码
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public UsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
//        return new MyUsernamePasswordAuthenticationFilter(authenticationManagerBean(),myAuthenticationSuccessHandler(),myAuthenticationFailureHandler());
//    }
}
