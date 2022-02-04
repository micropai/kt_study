package com.example.springbasic.etc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

public class StreamTest {

    @Test
    @DisplayName("[stream] empty 테스트")
    public void testNullStream(){
        // given

        // when & then
        then(Stream.empty()).isEmpty();
        then(Stream.empty()).contains();
        then(Stream.empty()).isNullOrEmpty();
        Stream<String> nullStream = null;
        then(nullStream).isNullOrEmpty();
    }

    @Test
    @DisplayName("[stream] 특정값 또는 null 포함 등의 기본테스트")
    public void testOfStream(){
        // given
        Stream<String> actual = Stream.of("a", "b", "c", null);
        // when & then
        then(actual)
                .hasSize(4)
                .containsNull()
                .contains("a", "b")
                .containsSequence("b", "c")
                .containsExactly("a", "b", "c", null);

    }

    @Test
    @DisplayName("[stream] stream 생성 add 와 of")
    public void testOfAdd(){
        // given
        Stream<Integer> actual = Stream.<Integer>builder().add(1).add(2).add(3).build();
        Stream<Integer> excepted = Stream.of(1, 2, 3);

        // when & then
        then(actual)
                .hasSize(3)
                .containsSequence(excepted.collect(Collectors.toList()));
    }
    
    @Test
    @DisplayName("[stream] stream generate 생성 ")
    public void testGenerateStream(){
        // given
        Stream<String> actual = Stream.generate(() -> "A").limit(5);
        // when & then
        then(actual)
                .hasSize(5)
                .containsExactly("A","A","A","A","A");
    
    }

    @Test
    @DisplayName("[stream] iterate 생성 테스트")
    public void testIterateStream(){
        // given
        Stream<Integer> actual = Stream.iterate(2, i-> i < 10, i-> i+2);
        // when & then
        then(actual)
                .hasSize(4)
                .doesNotContain(0, 10)
                .contains(6, 8)
                .containsExactly(2, 4, 6, 8);
    }

    @Test
    @DisplayName("[stream] 정규식 stream 생성")
    public void testRexStream(){
        // given
        Stream<String> actual = Pattern.compile(",").splitAsStream("a,b,c");
        // when & then
        then(actual)
                .hasSize(3)
                .containsExactly("a", "b", "c");
    }

    @Test
    @DisplayName("[stream] 예시")
    @Disabled
    public void testPrimitive(){
        // int stream 은 primitive type 입니다. boxed 를 통해 wapperclass 로 변환해야 합니다.
        // primitive stream 와 wapperclass || class 의 stream 은 서로 다른 메소드가 제공됩니다.
        IntStream.of(1, 2, 3, 1, 2, 3).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);
    }

    @Test
    @DisplayName("[stream] 특정값 또는 null 포함 등의 기본테스트")
    public void testContainsValue(){
        // given

        // when & then
        then(Stream.of("a", "b", "c", null))
                .hasSize(4)
                .containsNull()
                .contains("a", "b")
                .containsSequence("b", "c")
                .containsExactly("a", "b", "c", null);

    }

    @Test
    @DisplayName("[stream] sort 테스트")
    public void testSortTest(){
        // given
        // PriorityQueue 를 굳이 안써도 되지만 설명용으로 넣음
        List<Integer> list = List.of(1, 3, 3, 2, 1, 2);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        priorityQueue.addAll(list);

        // when
        // IntStream.of(1, 2, 3, 1, 2, 3).boxed().sorted(Collections.reverseOrder())
        Stream<Integer> actual = list.stream().sorted(Collections.reverseOrder());

        // then
        then(actual).allSatisfy(i -> then(i).isEqualTo(priorityQueue.poll()));
        // stream 이 닫혔기 때문에 실패나야 합니다.~
        thenThrownBy(()->then(actual).containsSequence(priorityQueue));
    }


}
