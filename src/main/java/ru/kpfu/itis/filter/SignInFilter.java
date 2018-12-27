package ru.kpfu.itis.filter;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.AuthRepositoryImpl;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepositoryImpl;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.client.ClientServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebFilter("/SignIn/client")
public class SignInFilter implements Filter {

    private ClientService clientService;

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DRIVER = "org.postgresql.Driver";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(URL);
        ClientsRepository clientsRepository = new ClientsRepositoryImpl(dataSource);
        AuthRepository authRepository = new AuthRepositoryImpl(dataSource);
        clientService = new ClientServiceImpl(clientsRepository, authRepository);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())) {
                        response.sendRedirect("/Page");
                        return;
//                        filterChain.doFilter(request, response);
                    }
                }
            }
            filterChain.doFilter(request, response);
//            response.sendRedirect("/Page");
            return;
        }
        filterChain.doFilter(request, response);
//        response.sendRedirect("Page");
        return;

    }


    @Override
    public void destroy() {

    }
}
