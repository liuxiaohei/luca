package org.ld.utils;

import java.util.Locale;

/**
 * Description: 国际化工具类
 */
public class I18N {

    /**
     * 业务文案, 放在values/biz_string.properties
     */
    public static String getString(String key, Object... args) {
        return I18nUtil.getLocalResource(Locale.getDefault(), "values/biz_strings", key, args);
    }

    /**
     * 错误文案, 放在values/error_strings.properties
     */
    public static String getErrorMsg(String key, Object... args) {
        return I18nUtil.getLocalResource(Locale.getDefault(), "values/error_strings", key, args);
    }
}
