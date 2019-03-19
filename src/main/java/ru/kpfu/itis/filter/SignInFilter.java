package ru.kpfu.itis.filter;

import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//@WebFilter(urlPatterns = {"/signIn", "/signUp", "/home"})
public class SignInFilter implements Filter {

    private ClientService clientService;
    private EmployeeService employeeService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        clientService = (ClientService) filterConfig.getServletContext().getAttribute("clientService");
        employeeService = (EmployeeService) filterConfig.getServletContext().getAttribute("employeeService");
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
                        response.setContentType("text/html");
                        response.sendRedirect("/profilePage");
                        return;
                    }
                    if (employeeService.isExistByCookie(cookie.getValue())){
                        response.setContentType("text/html");
                        response.sendRedirect("/adminPage");
                        return;
                    }
                }
            }
            if (request.getSession().getAttribute("client")!=null || request.getSession().getAttribute("employee")!=null) {
                if (request.getSession().getAttribute("client")!=null) {
                    response.setContentType("text/html");
                    response.sendRedirect("/profilePage");
                    return;
                }else {
                    response.setContentType("text/html");
                    response.sendRedirect("/adminPage");
                    return;
                }
            }else {
                filterChain.doFilter(request, response);
                return;
            }
        }
        if (request.getSession().getAttribute("client")!=null || request.getSession().getAttribute("employee")!=null) {
            if (request.getSession().getAttribute("client")!=null) {
                response.setContentType("text/html");
                response.sendRedirect("/profilePage");
                return;
            }else {
                response.setContentType("text/html");
                response.sendRedirect("/adminPage");
                return;
            }
        }else {
            filterChain.doFilter(request, response);
            return;
        }

        /*Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())) {
                        response.sendRedirect("/profilePage");
                        return;
                    }
                    if (employeeService.isExistByCookie(cookie.getValue())){
                        response.sendRedirect("/adminPage");
                        return;
                    }
                }
            }
            filterChain.doFilter(request, response);
//            response.sendRedirect("/Page");
            return;
        }*/
//        response.sendRedirect("Page");


    }


    @Override
    public void destroy() {

    }
}
