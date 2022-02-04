package com.example.springbasic.etc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.BDDAssertions.then;

public class LambdaTest {
    @Test
    @DisplayName("[lambda] Predicate test code")
    public void test(){
        // given

        // when
        Predicate<Integer> isEven = i -> i%2==0;
        // then
        then(isEven.test(3)).isFalse();
        then(isEven.test(8)).isTrue();

    }
}
