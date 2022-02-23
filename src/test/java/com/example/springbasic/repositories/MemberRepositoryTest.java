package com.example.springbasic.repositories;

import com.example.springbasic.entities.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("[db] test")
    void test(){
        // given

        // when
        memberRepository.save(Member.builder().memberName("name").build());
        // then

    }
}