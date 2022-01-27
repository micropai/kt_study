package com.example.springbasic.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class BasicCustomProp {
    @Value("${custom.basic.name}")
    private String name;
    private String url;
    private final String type;

    BasicCustomProp(@Value("${custom.basic.type}") String type){
        this.type = type;
    }


    @Autowired
    public void setProp(@Value("${custom.basic.url}")String url){
        this.url = url;
    }
}
