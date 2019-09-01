package com.example.boot.springboottemplatestarter.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * write this class description...
 *
 * @author Chang
 * @date 2019/9/1 12:44
 */
@Data
@ToString
@ConfigurationProperties(prefix = "custom-configuration.upload")
public class CustomUploadConfiguration {

    private DomainAndPath image;

    @Data
    public static class DomainAndPath {
        private String domain;
        private String path;
    }
}
