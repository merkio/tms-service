package io.space.geek.tms.core.converter;

import io.space.geek.tms.commons.constant.tms.PriorityTestCase;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToPriorityTestCase implements Converter<String, PriorityTestCase> {

    @Override
    public PriorityTestCase convert(@NonNull String source) {
        return PriorityTestCase.valueOf(source);
    }
}
