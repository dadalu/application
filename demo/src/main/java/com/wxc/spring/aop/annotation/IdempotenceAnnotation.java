package com.wxc.spring.aop.annotation;

import java.lang.annotation.*;

/**
 * @author admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdempotenceAnnotation {
    int type() default 1;
    long timeout() default 1000L;
}
