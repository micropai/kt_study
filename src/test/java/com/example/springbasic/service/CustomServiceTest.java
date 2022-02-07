package com.example.springbasic.service;

import com.example.springbasic.configuration.CommonConstant;
import com.example.springbasic.define.annt.CustomAntt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@Slf4j
@DisplayName("[annt] 커스텀 어노테이션")
class CustomServiceTest {
    @Test
    @DisplayName("[annt] 커스텀 어노테이션 테스트")
    void testCustomAnnotaion(){
        // given
        // when
        CustomAntt actual = CustomService.class.getAnnotation(CustomAntt.class);
        // then
        then(actual).isNotNull();
        then(actual.name()).isEqualTo(CommonConstant.ANTT_NAME);
        log.info(actual.name());
    }
}