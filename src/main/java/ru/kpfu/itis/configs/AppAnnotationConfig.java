package ru.kpfu.itis.configs;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

@ComponentScan({"ru.kpfu.itis", "ru.kpfu.itis.controllers", "ru.kpfu.itis.filter"})
@PropertySource("classpath:ru.kpfu.itis//application.properties")
public class AppAnnotationConfig {

  @Autowired
  private Environment environment;


  @Bean
  public FreeMarkerConfigurer freemarkerConfig() {
    FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
    freeMarkerConfigurer.setTemplateLoaderPaths(
            "/WEB-INF/templates/ftl/",
            "/WEB-INF/templates/ftl/client/",
            "/WEB-INF/templates/ftl/employee/",
            "/WEB-INF/templates/ftl/tours/"
    );
    freeMarkerConfigurer.setDefaultEncoding("UTF-8");
    return freeMarkerConfigurer;
  }

  @Bean(name = "freeMarkerViewResolver")
  public ViewResolver viewResolver() {
    FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
    viewResolver.setCache(true);
    viewResolver.setPrefix("");
    viewResolver.setSuffix(".ftl");
    viewResolver.setContentType("text/html; charset=UTF-8");
    return viewResolver;
  }


  @Bean
  public DataSource dataSourse(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setPassword(environment.getProperty("data.source.password"));
    dataSource.setUsername(environment.getProperty("data.source.username"));
    dataSource.setUrl(environment.getProperty("data.source.url"));
    dataSource.setDriverClassName(environment.getProperty("data.source.driver"));
    return dataSource;
  }


}
