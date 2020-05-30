package io.space.geek.tms.commons.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

import static org.springframework.util.StringUtils.countOccurrencesOf;

@Slf4j
public final class EmailUtils {

    public static String getDomain(String email) {
        if (email != null && countOccurrencesOf(email, "@") == 1) {
            return email.split("@")[1];
        }
        return StringUtils.EMPTY;
    }

    public static String getUsername(@NotNull String email) {
        if (countOccurrencesOf(email, "@") == 1) {
            return email.split("@")[0];
        }
        return StringUtils.EMPTY;
    }
}
