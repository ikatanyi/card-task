package com.logicea.card.config;

import org.apache.commons.codec.CharEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

  @Bean
  public ResourceBundleMessageSource messageSource() {
    var rs = new ResourceBundleMessageSource();
    rs.setBasename("messages");
    rs.setDefaultEncoding(CharEncoding.UTF_8);
    rs.setUseCodeAsDefaultMessage(true);
    return rs;
  }
}
