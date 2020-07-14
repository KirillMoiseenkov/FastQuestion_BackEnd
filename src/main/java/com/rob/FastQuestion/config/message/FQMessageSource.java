package com.rob.FastQuestion.config.message;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Locale;

@Service
public class FQMessageSource {
    private final MessageSource messageSource;
    private final Locale locale;

    public FQMessageSource(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String get(@NotNull String code) {
        return messageSource.getMessage(code, null, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg) {
        return messageSource.getMessage(code, new Object[]{arg}, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg1, @Nullable Object arg2) {
        return messageSource.getMessage(code, new Object[]{arg1, arg2}, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        return messageSource.getMessage(code, new Object[]{arg1, arg2, arg3}, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4) {
        return messageSource.getMessage(code, new Object[]{arg1, arg2, arg3, arg4}, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4, @Nullable Object arg5) {
        return messageSource.getMessage(code, new Object[]{arg1, arg2, arg3, arg4, arg5}, locale);
    }

    public String get(@NotNull String code, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object arg4, @Nullable Object arg5, @Nullable Object arg6) {
        return messageSource.getMessage(code, new Object[]{arg1, arg2, arg3, arg4, arg5, arg6}, locale);
    }

    public String get(@NotNull String code, @Nullable List<Object> args) {
        return messageSource.getMessage(code, args != null ? args.toArray() : null, locale);
    }

    public String get(@NotNull String code, @Nullable Object[] args) {
        return messageSource.getMessage(code, args, locale);
    }

    public String get(@NotNull String code, @Nullable Object[] args, Locale locale) {
        return messageSource.getMessage(code, args, locale);
    }

    public String get(@NotNull String code, @Nullable Object[] args, @Nullable String defaultMessage, Locale locale) {
        return messageSource.getMessage(code, args, defaultMessage, locale);
    }

    public String get(MessageSourceResolvable resolvable, Locale locale) {
        return messageSource.getMessage(resolvable, locale);
    }
}
