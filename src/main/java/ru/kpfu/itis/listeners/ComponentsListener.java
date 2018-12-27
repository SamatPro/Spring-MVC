package ru.kpfu.itis.context;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.AuthRepositoryImpl;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepositoryImpl;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.client.ClientServiceImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(URL);
        ClientsRepository clientsRepository = new ClientsRepositoryImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        ClientService clientService = new ClientServiceImpl(clientsRepository, authRepository);
        sce.getServletContext().setAttribute("clientService", clientService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
