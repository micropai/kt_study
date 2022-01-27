package com.example.springbasic.define.annt;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CustomAntt {
    String name() default "default-name";
}
