package com.rob.FastQuestion.config.message;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Locale;

@Configuration
public class LocaleConfig {

    /**
     * Русская локаль
     *
     * @return ruLocale
     */
    @Bean
    @Profile("!en")
    public Locale ruLocale() {
        Locale locale = new Locale("ru", "RU");
        Locale.setDefault(locale);
        return locale;
    }

    /**
     * Английская локаль
     *
     * @return enLocale
     */
    @Bean
    @Profile("en")
    public Locale enLocale() {
        Locale locale = Locale.ENGLISH;
        Locale.setDefault(locale);
        return locale;
    }
}
