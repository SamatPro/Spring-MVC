package ru.kpfu.itis.java;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//@Configuration
//@PropertySource("classpath:ru.kpfu.itis/application.properties")
public class JavaConfig {

    @Value("${data.source.url}")
    private String url;
    @Value("${data.source.username}")
    private String username;
    @Value("${data.source.password}")
    private String password;

    @Value("${data.source.driver}")
    private String driver;

    /*@Bean
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        return dataSource;
    }

    @Bean
    public ClientsRepository clientsRepository(){
        return new ClientsRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public AuthRepository authRepository(){
        return new AuthRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CountriesRepository countriesRepository(){
        return new CountriesRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CitiesRepository citiesRepository(){
        return new CitiesRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public CooperativeToursRepository cooperativeToursRepository(){
        return new CooperativeToursRepositoryImpl(dataSource());
    }

    @Bean
    public EmployeesRepository employeesRepository(){
        return new EmployeesRepositoryImpl(dataSource());
    }

    @Bean
    public OrdersRepository ordersRepository(){
        return new OrdersRepositoryImpl(dataSource());
    }

    @Bean
    public PicturesRepository picturesRepository(){
        return new PicturesRepositoryJdbcTemplateImpl(dataSource());
    }

    @Bean
    public ServicesRepository servicesRepository(){
        return new ServicesRepositoryJdbcTemplateImpl(dataSource());
    }
*/
}
