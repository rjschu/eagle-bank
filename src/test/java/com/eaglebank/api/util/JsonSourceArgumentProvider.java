package com.eaglebank.api.util;

import org.aspectj.util.FileUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.stream.Stream;

public class JsonSourceArgumentProvider implements ArgumentsProvider, AnnotationConsumer<JsonSource> {

    private String file;

    @Override
    public void accept(JsonSource jsonSource) {
        this.file = jsonSource.file();
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        ClassPathResource resource = new ClassPathResource(file);
        String result = FileUtil.readAsString(resource.getFile());
        return Stream.of(Arguments.of(result));
    }
}
