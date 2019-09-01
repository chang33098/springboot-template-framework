package com.example.boot.springboottemplatestarter.properties;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EDZ on 2019/8/5.
 */
@Data
@ToString
@ConfigurationProperties(prefix = "custom-configuration.view")
public class CustomViewConfiguration {

    private List<View> commonViews = new ArrayList<>();
    private List<View> bizViews = new ArrayList<>();
    private List<View> errorViews = new ArrayList<>();

    @Data
    public static class View {
        private String path;
        private String name;
    }
}
