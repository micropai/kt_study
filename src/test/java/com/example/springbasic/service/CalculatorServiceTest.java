package com.example.springbasic.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("연산테스트")
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest
class CalculatorServiceTest {

    private final CalculatorService calculatorService;

    public CalculatorServiceTest(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @Test
    @DisplayName("[service] 합 연산 테스트")
    void sum() {
        // given
        final int a = 3;
        final int b = 6;
        // when
        int actual = calculatorService.sum(a, b);
        // then
        assertThat(actual).isEqualTo(a+b);
    }

    @Test
    @DisplayName("[service] 차 연산 테스트")
    void minus() {
        // given
        final int a = 2_000;
        final int b = 5_000;

        // when
        int actual = calculatorService.minus(a, b);
        // then
        assertThat(actual).isEqualTo(a-b);
    }
}