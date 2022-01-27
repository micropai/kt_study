package com.example.springbasic.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * 생성자를 통해 커스텀 프로퍼티를 세팅
 */
@ConfigurationProperties("custom.construct")
@ConstructorBinding
@RequiredArgsConstructor
@Getter
public class ConstructCustomProp {
    private final String type;
    private final String name;
    private final String url;
}
