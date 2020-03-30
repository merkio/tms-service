package com.company.tms.commons.util;


import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.beanutils.PropertyUtils;

import java.util.Map;

import static java.util.Collections.singleton;
import static org.apache.commons.beanutils.BeanUtils.populate;

public final class BeanUtils {

    @SneakyThrows
    public static void copyNonNullProperties(@NonNull Object source, @NonNull Object destination) {
        populate(destination, collectNonNullProperties(source));
    }

    @SneakyThrows
    public static Map<String, Object> collectProperties(@NonNull Object bean) {
        Map<String, Object> properties = PropertyUtils.describe(bean);
        properties.remove("class");
        return properties;
    }

    @SneakyThrows
    public static Map<String, Object> collectNonNullProperties(@NonNull Object bean) {
        Map<String, Object> map = collectProperties(bean);
        map.values().removeAll(singleton(null));
        return map;
    }
}
