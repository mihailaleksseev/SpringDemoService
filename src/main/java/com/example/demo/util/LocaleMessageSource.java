package com.example.demo.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

public class LocaleMessageSource extends ResourceBundleMessageSource {
    public String getMessage(String message) {
        return getMessage(message, new Object[] {}, message, LocaleContextHolder.getLocale());
    }

    public String getMessage(String message, Object... params) {
        return getMessage(message, params, message, LocaleContextHolder.getLocale());
    }
}
