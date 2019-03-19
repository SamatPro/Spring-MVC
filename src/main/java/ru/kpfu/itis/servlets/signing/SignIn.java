package ru.kpfu.itis.servlets.signing;

import lombok.SneakyThrows;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.auths.AuthRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

//@WebServlet("/signIn")
public class SignIn extends HttpServlet {

    private ClientService clientService;
    private EmployeeService employeeService;

    @Override
    @SneakyThrows
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
        this.employeeService = (EmployeeService) context.getAttribute("employeeService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/ftl/login.ftl").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean remember = Boolean.parseBoolean(request.getParameter("remember"));
        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();
        if (remember){
            Optional<String> cookieValueCandidate = clientService.signIn(loginForm);
            if (cookieValueCandidate.isPresent()) {
                Client client = clientService.getClientByCookie(cookieValueCandidate.get());
                Cookie authCookie = new Cookie("auth", cookieValueCandidate.get());
                authCookie.setMaxAge(60*60*24*30);
                response.addCookie(authCookie);
                response.setContentType("text/html");
                response.sendRedirect("/profilePage");
                return;
            }
            Optional<String> cookieValueEmployee = employeeService.signIn(loginForm);
            if (cookieValueEmployee.isPresent()) {
                Employee employee = employeeService.getEmployeeByCookie(cookieValueEmployee.get());
                Cookie authCookie = new Cookie("auth", cookieValueEmployee.get());
                authCookie.setMaxAge(60*60*24*30);
                response.addCookie(authCookie);
                response.setContentType("text/html");
                response.sendRedirect("/adminPage");
                return;
            }
        }else {
            Long sessionCandiateId = clientService.signInBySession(loginForm);
            if (sessionCandiateId != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("client", sessionCandiateId);
                response.setContentType("text/html");
                response.sendRedirect("/profilePage");
                return;
            }
            Long cookieValueEmployee = employeeService.signInBySession(loginForm);
            if (cookieValueEmployee != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("employee", cookieValueEmployee);
                response.setContentType("text/html");
                response.sendRedirect("/adminPage");
                return;
            }
        }
//        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/ftl/login.ftl").forward(request, response);

    }

}
