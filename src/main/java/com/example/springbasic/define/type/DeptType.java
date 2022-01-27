package com.example.springbasic.define.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DeptType {
    ACCOUNTING("0001"),
    DEVELOP("0001"),
    SALES("0002"),
    MATERIALS("0003"),
    NONE("9999");

    private final String detpCode;

    public static DeptType getDept(String dept){
        return Arrays.stream(DeptType.values())
                .filter(t -> t.name().equals(dept))
                .findFirst()
                .orElse(DeptType.NONE);
    }
}
