package ru.kpfu.itis.servlets.signing;

import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.AuthRepositoryImpl;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepositoryImpl;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.client.ClientServiceImpl;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;

@WebServlet("/signIn")
public class SignInClient extends HttpServlet {

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
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);

//        request.getRequestDispatcher("/jsp/Simple.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginForm loginForm = LoginForm.builder()
                .email(email)
                .password(password)
                .build();

        Optional<String> cookieValueCandidate = clientService.signIn(loginForm);
        if (cookieValueCandidate.isPresent()) {
            Cookie auth = new Cookie("auth", cookieValueCandidate.get());
            auth.setMaxAge(60*60*3);
            response.addCookie(auth);
            response.sendRedirect("/profilePage");
            return;
        }
        Optional<String> cookieValueEmployee = employeeService.signIn(loginForm);
        if (cookieValueEmployee.isPresent()) {
            Cookie auth = new Cookie("auth", cookieValueEmployee.get());
            auth.setMaxAge(60*60*3);
            response.addCookie(auth);
            response.sendRedirect("/adminPage");
            return;
        }
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);



        /*clientService.signIn(loginForm);
        PrintWriter writer = response.getWriter();
        writer.write("Hello");*/
    }

}
