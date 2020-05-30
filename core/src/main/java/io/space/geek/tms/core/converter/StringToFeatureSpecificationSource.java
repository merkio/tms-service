package io.space.geek.tms.core.converter;

import io.space.geek.tms.commons.constant.tms.FeatureSpecificationSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToFeatureSpecificationSource implements Converter<String, FeatureSpecificationSource> {

    @Override
    public FeatureSpecificationSource convert(String s) {
        return FeatureSpecificationSource.valueOf(s.toUpperCase());
    }
}
