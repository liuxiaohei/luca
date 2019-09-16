package org.ld.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Description: 国际化工具类
 */
public class I18N {

    /**
     * biz_string.properties
     */
    public static String getString(String key, Object... args) {
        return getLocalResource(Locale.getDefault(), "values/biz_strings", key, args);
    }

    /**
     * error_strings.properties
     */
    public static String getErrorMsg(String key, Object... args) {
        return getLocalResource(Locale.getDefault(), "values/error_strings", key, args);
    }

    private static String getLocalResource(Locale locale, String baseName, String key, Object... args) {
        return Optional.of(ResourceBundle.getBundle(baseName, locale))
                .map(rb -> rb.getString(key))
                .map(message -> {
                    if (args != null && args.length > 0) {
                        return MessageFormat.format(message, args);
                    } else {
                        return message;
                    }
                }).orElse(null);
    }
}
