package com.eaglebank.api.util;

import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.*;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(JsonSourceArgumentProvider.class)
public @interface JsonSource {

    String file();
}
