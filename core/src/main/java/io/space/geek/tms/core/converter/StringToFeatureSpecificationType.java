package io.space.geek.tms.core.converter;

import io.space.geek.tms.commons.constant.tms.FeatureSpecificationType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToFeatureSpecificationType implements Converter<String, FeatureSpecificationType> {

    @Override
    public FeatureSpecificationType convert(@NonNull String source) {
        return FeatureSpecificationType.valueOf(source.toUpperCase());
    }
}
