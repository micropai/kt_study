package com.example.springbasic.configuration;

import com.example.springbasic.service.CustomService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicConfiguration {
    /**
     * 빈 직접등록 가능하며 기본적으로 method 명으로 bean 이름이 정해진다
     * 빈이름 지정이 가능하며 중복되면 안된다.
     * bean 의 생명주기의 생성 소멸 호출방법은
     * 스프링에서 일반적으로 제공하는 방법들이다.
     * {@link Bean} 생성시 메소드명을 지정, @Postcustruct 으로 직접 지정
     * {@link org.springframework.beans.factory.InitializingBean} 인터페이스 상속을 통한 오버라이딩 이 있음
     * @return {@link CustomService}
     */
    @Bean
    public CustomService customService(){
        return new CustomService();
    }
}
