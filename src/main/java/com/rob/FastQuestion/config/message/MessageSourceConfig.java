package com.rob.FastQuestion.config.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageSourceConfig {
    /**
     * Поддержка messages файлов:
     * 1. event - для событий
     * 2. exception - для исключений
     *
     * @return MessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(
                "classpath:/messages/event/event",
                "classpath:/messages/exception/exception"
        );
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    /**
     * Смена локали
     *
     * @return FixedLocaleResolver
     */
    @Bean
    public LocaleResolver localeResolver(Locale locale) {
        return new FixedLocaleResolver(locale);
    }
}
