package com.hyt.server.anno;

import java.lang.annotation.*;

/**
 * 定义国突对接注解
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Emergency {
    String value() default "";
}
