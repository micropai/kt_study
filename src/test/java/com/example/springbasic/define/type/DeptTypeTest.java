package com.example.springbasic.define.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.*;

class DeptTypeTest {
    @Test
    @DisplayName("[enum] string 으로 부서 조회")
    public void testGetDeptString(){
        // given
        final DeptType expected = DeptType.MATERIALS;
        // when
        DeptType actual = DeptType.getDept(expected.name());
        // then
        then(actual).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("[enum] 존재하지 않는 부서인 경우 처리")
    public void testNotFoundDept(){
        // given
        final String dept = "aabbcc";
        // when
        DeptType actual = DeptType.getDept(dept);
        // then
        then(actual).isEqualTo(DeptType.NONE);
    }
}