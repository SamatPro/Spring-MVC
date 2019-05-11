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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

@ComponentScan({"ru.kpfu.itis", "ru.kpfu.itis.controllers", "ru.kpfu.itis.filter"})
@PropertySource("classpath:ru.kpfu.itis//application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ru.kpfu.itis.repositories.auths", "ru.kpfu.itis.repositories.cities","ru.kpfu.itis.repositories.clients","ru.kpfu.itis.repositories.orders","ru.kpfu.itis.repositories.pictures","ru.kpfu.itis.repositories.employees"})
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
  public DataSource dataSource(){
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setPassword(environment.getProperty("data.source.password"));
    dataSource.setUsername(environment.getProperty("data.source.username"));
    dataSource.setUrl(environment.getProperty("data.source.url"));
    dataSource.setDriverClassName(environment.getProperty("data.source.driver"));
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager transactionManager
            = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(
            entityManagerFactory().getObject());
    return transactionManager;
  }

  private Properties hibernateProperties() {
    Properties hibernateProperties = new Properties();
    String[] propsNames = {"hibernate.hbm2ddl.auto", "hibernate.dialect",
            "hibernate.show_sql"};
    Arrays.stream(propsNames).forEach(propName ->
            hibernateProperties.setProperty(propName,
                    environment.getProperty(propName)));
    return hibernateProperties;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em
            = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("ru.kpfu.itis.models");

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    em.setJpaProperties(hibernateProperties());
    return em;
  }


}
