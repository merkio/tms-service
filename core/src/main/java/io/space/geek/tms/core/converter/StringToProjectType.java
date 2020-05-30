package io.space.geek.tms.core.converter;

import io.space.geek.tms.commons.constant.tms.ProjectType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProjectType implements Converter<String, ProjectType> {

    @Override
    public ProjectType convert(String s) {
        return ProjectType.valueOf(s.toUpperCase());
    }
}
