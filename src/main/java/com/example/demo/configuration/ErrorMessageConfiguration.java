package com.example.demo.configuration;

import com.example.demo.util.LocaleMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ErrorMessageConfiguration {

    @Bean
    public LocaleMessageSource localeMessageSource() {
        LocaleMessageSource messageSource = new LocaleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("messages");
        return messageSource;
    }

}
