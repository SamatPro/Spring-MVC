package ru.kpfu.itis.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.client.ClientServiceImpl;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Autowired
    @Qualifier("clientService")
    private ClientService clientService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    System.out.println(clientService);
                    if (clientService.isExistByCookie(cookie.getValue())) {
                        System.out.println(clientService.isExistByCookie(cookie.getValue()));
                        filterChain.doFilter(request, response);
                        return;
                    }
                }
            }
            if (request.getSession().getAttribute("client")!=null || request.getSession().getAttribute("employee")!=null) {
                filterChain.doFilter(request, response);
                return;
            }else {
                response.setContentType("text/html");
                response.sendRedirect("/signIn");
                return;
            }
        }
        if (request.getSession().getAttribute("client")!=null || request.getSession().getAttribute("employee")!=null) {
            filterChain.doFilter(request, response);
            return;
        }else {
            response.setContentType("text/html");
            response.sendRedirect("/signIn");
            return;
        }
    }


    @Override
    public void destroy() {

    }
}
