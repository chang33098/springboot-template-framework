package com.example.boot.springboottemplatestarter.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/8/4 21:55
 */
@Data
@ToString
@ConfigurationProperties(prefix = "custom-configuration.security")
public class CustomSecurityConfiguration {

    private SuperAdminConfig superAdmin;
    private FormLoginConfig formLogin;
    private LogoutConfig logout;
    private RememberMeConfig rememberMe;

    private List<String> permitMatchUrls = new ArrayList<>();

    @Data
    public static class SuperAdminConfig {
        private String username;
        private String password;
    }

    @Data
    public static class FormLoginConfig {
        private String usernameParam = "username";
        private String passwordParam = "password";
        private String loginPage = "/login";
        private String loginProcessingUrl;
        private String defaultSuccessUrl;
        private String failureUrl;
    }

    @Data
    public static class LogoutConfig {
        private String logoutUrl = "/logout";
        private String logoutSuccessUrl;
        private Boolean invalidateSession = true;
    }

    @Data
    public static class RememberMeConfig {
        private String rememberMeParam;
        private Integer tokenValiditySeconds = 3600;
    }
}
