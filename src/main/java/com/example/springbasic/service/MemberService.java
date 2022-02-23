package com.example.springbasic.service;

import com.example.springbasic.controller.MemberRequest;
import com.example.springbasic.entities.Member;
import com.example.springbasic.repositories.MemberRepository;
import com.example.springbasic.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member selectById(Long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    public <T> T selectById(Long id, Class<T> clazz) {
        return ModelMapperUtils.map(memberRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(id + " Element not exist ")), clazz);
    }


    public <T> List<T> select(MemberRequest req, Class<T> clazz) {
        //querydsl로 붙일 예정
        return ModelMapperUtils.mapList(memberRepository.findAll(), clazz);
    }

    @Transactional
    public Member insert(MemberRequest request) {
        return memberRepository.save(ModelMapperUtils.map(request, Member.class));
    }

    @Transactional
    public <T> T insert(MemberRequest request, Class<T> clazz) {
        return ModelMapperUtils.map(insert(request), clazz);
    }

}
