package com.ly.mymovie.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by LanYang on 2017/12/07.
 * Email:568240761@qq.com
 * 该注解表示Activity类型的Context
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
