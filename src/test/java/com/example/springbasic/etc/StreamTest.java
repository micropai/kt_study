package com.example.springbasic.etc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@DisplayName("[stream] test code")
public class StreamTest {

    @Test
    @DisplayName("[생성] empty 테스트")
    void testNullStream(){
        // given

        // when & then
        then(Stream.empty()).isEmpty();
        then(Stream.empty()).contains();
        then(Stream.empty()).isNullOrEmpty();
        Stream<String> nullStream = null;
        then(nullStream).isNullOrEmpty();
    }

    @Test
    @DisplayName("[생성] 특정값 또는 null 포함 등의 기본테스트")
    void testOfStream(){
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
    @DisplayName("[생성] stream 생성 add 와 of")
    void testOfAdd(){
        // given
        Stream<Integer> actual = Stream.<Integer>builder().add(1).add(2).add(3).build();
        Stream<Integer> excepted = Stream.of(1, 2, 3);

        // when & then
        then(actual)
                .hasSize(3)
                .containsSequence(excepted.collect(Collectors.toList()));
    }
    
    @Test
    @DisplayName("[생성] stream generate 생성 ")
    void testGenerateStream(){
        // given
        Stream<String> actual = Stream.generate(() -> "A").limit(5);
        // when & then
        then(actual)
                .hasSize(5)
                .containsExactly("A","A","A","A","A");
    
    }

    @Test
    @DisplayName("[생성] iterate 생성 테스트")
    void testIterateStream(){
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
    @DisplayName("[생성] 정규식 stream 생성")
    void testRexStream(){
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
    void testPrimitive(){
        // int stream 은 primitive type 입니다. boxed 를 통해 wapperclass 로 변환해야 합니다.
        // primitive stream 와 wapperclass || class 의 stream 은 서로 다른 메소드가 제공됩니다.
        IntStream.of(1, 2, 3, 1, 2, 3).boxed().sorted(Collections.reverseOrder()).forEach(System.out::println);
    }

    @Test
    @DisplayName("[생성] 특정값 또는 null 포함 등의 기본테스트")
    void testContainsValue(){
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
    @DisplayName("[가공] sort 테스트")
    void testSortTest(){
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


    @Test
    @DisplayName("[가공] stream filter 테스트")
    void testFilterProcessing(){
        // given
        // when
        IntStream actual = IntStream.range(0, 10).filter(i -> i % 2 == 0);
        // then
        then(actual).hasSize(5).allSatisfy(i -> then( i % 2 == 0).isTrue());
    }
    
    @Test
    @DisplayName("[가공] stream limit 테스트")
    void testLimitProcessing(){
        // given

        // when
        IntStream actual = IntStream.range(0, 10).limit(3);
        // then
        then(actual).hasSize(3);
    }
    
    @Test
    @DisplayName("[가공] stream map 테스트")
    void test(){
        // given
        
        // when
        Stream<String> actual = IntStream.range(0, 10).mapToObj(Integer::toBinaryString);
        // then
        then(actual)
                .zipSatisfy(IntStream.range(0, 10).boxed().collect(Collectors.toList())
                        , (binaryStr, i)->then(Integer.parseInt(binaryStr, 2)).isEqualTo(i));
    }


    @Test
    @DisplayName("[가공] flatMap")
    void testFlatMap(){
        // given
        List<Student> students = Arrays.asList(
                new Student("a", 17, 65, 83, 92),
                new Student("b", 17, 75, 77, 78),
                new Student("c", 19, 85, 88, 85),
                new Student("d", 19, 90, 81, 84));
        // when
        double actual = students.stream()
                .flatMapToInt(s ->
                        IntStream.of(s.getKor(), s.getEng(), s.getMath()))
                .average()
                .orElse(0);

        double expected = students.stream()
                .mapToDouble(s -> IntStream.of(s.getKor(), s.getEng(), s.getMath())
                        .average().orElse(0))
                .average()
                .orElse(0);
        // then
        then(actual).isEqualTo(expected);
    }
    // TODO 결과
    // reduce 나 int 형태의 써머리 , 종합 써머리, group 등 추가
    // 학년별로
    static class Student {
        private final String name;
        // 학년
        private final int grade;
        private final int kor;
        private final int eng;
        private final int math;


        public Student(String name, int age, int kor, int eng, int math) {
            this.name = name;
            this.grade = age;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }

        public int getKor() {
            return kor;
        }

        public int getEng() {
            return eng;
        }

        public int getMath() {
            return math;
        }
    }

}
