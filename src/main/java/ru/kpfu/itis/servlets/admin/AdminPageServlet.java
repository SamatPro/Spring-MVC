package ru.kpfu.itis.servlets.admin;

import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

//@WebServlet("/adminPage")
public class AdminPageServlet extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Employee employee = employeeService.getEmployee(request);

        request.setAttribute("employeeFirstName", employee.getFirstName());
        request.setAttribute("employeeLastName", employee.getLastName());

//        request.getRequestDispatcher("WEB-INF/jsp/employee/employeePage.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/employee/employeePage.ftl").forward(request, response);

    }
}
