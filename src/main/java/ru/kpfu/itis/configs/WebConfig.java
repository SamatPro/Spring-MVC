package ru.kpfu.itis.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("ru.kpfu.itis.controllers")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/resources/css/");
    registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
    registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/resources/scripts/");
    registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/resources/img/");
  }

}
