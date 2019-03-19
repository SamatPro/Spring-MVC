package ru.kpfu.itis.listeners;

import ru.kpfu.itis.localization.Localizations;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Map;

public class LocaleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        Map<String, String> localeRu = Localizations.loadLocalization("ru");
        Map<String, String> localeEn = Localizations.loadLocalization("en");
        context.setAttribute("localeRu", localeRu);
        context.setAttribute("localeEn", localeEn);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
