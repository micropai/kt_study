package com.example.springbasic.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * ConfigurationProperties 를 통한 프로퍼티 바인딩
 * yml 로 여러 키를 가진 list object 로도 바인딩됩니다.
 * 최신 프로퍼티에서는 yml 이 아니고 properties 파일도 가능함 추후 예시추가
 */
@ConfigurationProperties("custom.configuration")
@Getter
@Setter
public class ConfigCustomProp {
    private String type;
    private String name;
    private String url;
}
