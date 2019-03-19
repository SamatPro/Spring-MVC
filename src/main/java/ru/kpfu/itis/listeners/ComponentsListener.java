package ru.kpfu.itis.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.kpfu.itis.repositories.auths.AuthRepository;
import ru.kpfu.itis.repositories.cities.CitiesRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.cooperativeTours.CooperativeToursRepository;
import ru.kpfu.itis.repositories.countries.CountriesRepository;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.repositories.pictures.PicturesRepository;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ComponentsListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("ru.kpfu.itis/context.xml");
        ClientService clientService = context.getBean(ClientService.class);
        ClientsRepository clientsRepository = context.getBean(ClientsRepository.class);
        EmployeeService employeeService = context.getBean(EmployeeService.class);
        EmployeesRepository employeesRepository = context.getBean(EmployeesRepository.class);
        AuthRepository authRepository = context.getBean(AuthRepository.class);
        OrdersRepository ordersRepository = context.getBean(OrdersRepository.class);
        CitiesRepository citiesRepository = context.getBean(CitiesRepository.class);
        CountriesRepository countriesRepository = context.getBean(CountriesRepository.class);
        CooperativeToursRepository cooperativeToursRepository = context.getBean(CooperativeToursRepository.class);
        PicturesRepository picturesRepository = context.getBean(PicturesRepository.class);

        sce.getServletContext().setAttribute("clientRepository", clientsRepository);
        sce.getServletContext().setAttribute("clientService", clientService);
        sce.getServletContext().setAttribute("authRepository", authRepository);
        sce.getServletContext().setAttribute("employeeService", employeeService);
        sce.getServletContext().setAttribute("employeeRepository", employeesRepository);
        sce.getServletContext().setAttribute("orderRepository", ordersRepository);
        sce.getServletContext().setAttribute("cityRepository", citiesRepository);
        sce.getServletContext().setAttribute("cooperativeTours", cooperativeToursRepository);
        sce.getServletContext().setAttribute("picturesRepository", picturesRepository);
        sce.getServletContext().setAttribute("countriesRepository", countriesRepository);



    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
