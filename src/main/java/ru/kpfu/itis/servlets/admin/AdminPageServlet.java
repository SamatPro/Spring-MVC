package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
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
import java.util.Optional;

@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {
    EmployeeService employeeService;
    EmployeesRepository employeesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.employeeService = (EmployeeService) context.getAttribute("employeeService");
        this.employeesRepository = (EmployeesRepository) context.getAttribute("employeeRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (employeeService.isExistByCookie(cookie.getValue())){
                        Optional<Employee> employeeOptional = employeesRepository.findEmployeeByCookie(cookie.getValue());
                        Employee employee = employeeOptional.get();
                        request.setAttribute("employeeFirstName", employee.getFirstName());
                        request.setAttribute("employeeLastName", employee.getLastName());

                    }
                }
            }
        }

        request.getRequestDispatcher("jsp/employeePage.jsp").forward(request, response);
    }
}
