package com.example.springbasic.etc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.*;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@DisplayName("[lambda] test code")
public class LambdaTest {
    @Test
    @DisplayName("[lambda] Predicate test code")
    void testPredicate(){
        // given
        Predicate<Integer> isEven = i -> i % 2 == 0;
        // when & then
        then(isEven.test(3)).isFalse();
        then(isEven.test(8)).isTrue();

    }

    @Test
    @DisplayName("[lambda] Function test code")
    void testFunction(){
        // given
        Function<Integer, Integer> addThree = (a)-> a+3;
        // when & then
        then(addThree.apply(3)).isEqualTo(6);
        then(addThree.apply(8)).isEqualTo(11);

    }

    @Test
    @DisplayName("[lambda] Cumsumer test code")
    void testCumsumer(){
        // given
        Consumer<String> consumer = s -> then(s).startsWith("kt");
        // when & then
        consumer.accept("kt.com");
    }

    @Test
    @DisplayName("[lambda] Supplier test code")
    void testSupplier(){
        // given
        Supplier<String> supplier = ()-> UUID.randomUUID().toString();
        // when & then
        // uuid 중복가능하지만 확률이 극히 낮을 뿐. 정확한 테스트는 아님
        then(supplier.get()).isNotEqualTo(supplier.get());
    }

    @Test
    @DisplayName("[lambda] enum 으로 람다 사용 예시")
    void testEnum(){
        // given
        final Integer a = 5_543;
        final Integer b = 1_001;
        // when & then
        then(Operate.apply("+", a, b)).isEqualTo(a+b);
        then(Operate.apply("-", a, b)).isEqualTo(a-b);
        then(Operate.apply("*", a, b)).isEqualTo(a*b);
        then(Operate.apply("/", a, b)).isEqualTo(a/b);
        thenThrownBy(()->Operate.apply("#", a, b));
    }


    enum Operate{
        ADD("+", (a,b)->a+b),
        MINUS("-", (a,b)->a-b),
        MULTIPLY("*", (a,b)->a*b),
        DIVIDE("/", (a,b)->a/b);

        private final String oper;
        private final BiFunction<Integer, Integer, Integer> fn;

        Operate(String oper, BiFunction<Integer, Integer, Integer> fn) {
            this.oper = oper;
            this.fn = fn;
        }

        protected String getOper() {
            return oper;
        }

        protected BiFunction<Integer, Integer, Integer> getFn() {
            return fn;
        }

        public static Integer apply(String oper, Integer a, Integer b){
            return getOperate(oper).getFn().apply(a, b);
        }

        public static Operate getOperate(String oper) {
            return Arrays.stream(Operate.values())
                    .filter(v -> v.getOper().equals(oper))
                    .findFirst()
                    .orElseThrow();
        }
    }

    @Test
    @DisplayName("[lambda] Thread test code")
    @Timeout(1)
    void testThread(){
        // given

        // when & then
        new Thread(()-> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // check exception 은 Runnable 에서 처리하지 않기 때문에
                // 내부적으로 처리해 줘야 합니다.
                e.printStackTrace();
            }
        }).start();
    }
}
