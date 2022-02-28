package com.harukaze.front.aop;

import java.lang.annotation.*;

//Type代表可以放在类上
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    String module() default "";

    String operator() default "";
}
