package com.company.tms.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nonnull;

import static org.springframework.util.StringUtils.countOccurrencesOf;

@Slf4j
public final class EmailUtils {

    public static String getDomain(String email) {
        if (email != null && countOccurrencesOf(email, "@") == 1) {
            return email.split("@")[1];
        }
        return StringUtils.EMPTY;
    }

    public static String getUsername(@Nonnull String email) {
        if (countOccurrencesOf(email, "@") == 1) {
            return email.split("@")[0];
        }
        return StringUtils.EMPTY;
    }
}
