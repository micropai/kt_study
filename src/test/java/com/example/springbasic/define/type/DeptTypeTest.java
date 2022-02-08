package com.example.springbasic.define.type;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.then;

@DisplayName("[enum] enum 사용법을 위한 테스트 코드")
class DeptTypeTest {
    @Test
    @DisplayName("[enum] string 으로 부서 조회")
    void testGetDeptString(){
        // given
        final DeptType expected = DeptType.MATERIALS;
        // when
        DeptType actual = DeptType.getDept(expected.getDetpCode());
        // then
        then(actual).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("[enum] 존재하지 않는 부서인 경우 처리")
    void testNotFoundDept(){
        // given
        final String dept = "aabbcc";
        // when
        DeptType actual = DeptType.getDept(dept);
        // then
        then(actual).isEqualTo(DeptType.NONE);
    }

}