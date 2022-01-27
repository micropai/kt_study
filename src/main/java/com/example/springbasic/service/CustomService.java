package com.example.springbasic.service;

import com.example.springbasic.configuration.CommonConstant;
import com.example.springbasic.define.annt.CustomAntt;

/**
 * 같은 어노테이션의 멀티선언도 가능합니다.
 * 단 @Repeatable 통해서 해야합니다.
 */
@CustomAntt(name = CommonConstant.ANTT_NAME)
public class CustomService {
}
