package com.example.springbasic.etc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.PrimitiveType;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;

public class StreamTest {


    @Test
    @DisplayName("[stream] 예시")
    public void testPrimitive(){
        // int stream 은 primitive type 입니다. boxed 를 통해 wapperclass 로 변환해야 합니다.
        // primitive stream 와 wapperclass || class 의 stream 은 서로 다른 메소드가 제공됩니다.
        IntStream.of(1, 2, 3, 1, 2, 3).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);
    }

    @Test
    @DisplayName("[stream] sort test")
    public void testSortTest(){
        // given
        List<Integer> list = List.of(1, 3, 3, 2, 1, 2);

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        list.stream().forEach(priorityQueue::add);

        // when
        // IntStream.of(1, 2, 3, 1, 2, 3).boxed().sorted(Collections.reverseOrder())
        Stream<Integer> actual = list.stream().sorted(Collections.reverseOrder());

        // then
        then(actual).allSatisfy(i -> {
            then(i).isEqualTo(priorityQueue.poll());
        });
        // stream 이 닫혔기 때문에 실패나야 합니다.~
        then(actual).containsSequence(priorityQueue);
    }


}
