package com.example.springbasic.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("custom.configuration")
@Getter
@Setter
public class ConfigCustomProp {
    private String type;
    private String name;
    private String url;
}
