package ru.kpfu.itis.app;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.kpfu.itis.configs.AppAnnotationConfig;
import ru.kpfu.itis.filter.LocalizationFilter;
import ru.kpfu.itis.filter.LogFilter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
  @Override
  public void onStartup(ServletContext container) throws ServletException {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppAnnotationConfig.class);
    context.setServletContext(container);
    DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
    ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcherServlet);
    servlet.setLoadOnStartup(1);
    servlet.addMapping("/");
//    container.addFilter("logFilter", LogFilter.class).addMappingForUrlPatterns(null, false, "/*");
    container.addFilter("localizationFilter", LocalizationFilter.class).addMappingForUrlPatterns(null, false, "/*");
  }
}
