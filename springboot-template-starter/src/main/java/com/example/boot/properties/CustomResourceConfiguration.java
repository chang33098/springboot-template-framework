package com.example.boot.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDZ on 2019/8/5.
 */
@Data
@ToString
@ConfigurationProperties(prefix = "custom-configuration.static")
public class CustomResourceConfiguration {

    private List<ResourceHandler> resources = new ArrayList<>();

    @Data
    public static class ResourceHandler {
        private String pathPattern;
        private String location;
    }
}
