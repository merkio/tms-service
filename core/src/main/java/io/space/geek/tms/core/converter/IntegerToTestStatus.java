package io.space.geek.tms.core.converter;

import io.space.geek.tms.commons.constant.tms.TestStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class IntegerToTestStatus implements Converter<Integer, TestStatus> {

    @Override
    public TestStatus convert(@NonNull Integer source) {
        return TestStatus.testStatusByValue(source);
    }
}
