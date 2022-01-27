package com.example.springbasic.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 기본적인 프로퍼티 바인딩 예시
 */
@Configuration
@Getter
public class BasicCustomProp {
    /**
     * 기본적인 value 어노테이션을 통한 바인딩
     */
    @Value("${custom.basic.name}")
    private String name;
    
    private String url;
    private final String type;

    /**
     * 생성자를 통한 프로퍼티 바인딩
     * @param type
     */
    BasicCustomProp(@Value("${custom.basic.type}") String type){
        this.type = type;
    }

    /**
     * autowired 를 통한 프로퍼티 바인딩
     */
    @Autowired
    public void setProp(@Value("${custom.basic.url}")String url){
        this.url = url;
    }
}
