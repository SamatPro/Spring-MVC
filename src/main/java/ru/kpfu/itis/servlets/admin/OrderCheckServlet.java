package ru.kpfu.itis.servlets.admin;

import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
//@WebServlet("/order/check")
public class OrderCheckServlet  extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Employee employee = employeeService.getEmployee(request);

        request.setAttribute("employeeFirstName", employee.getFirstName());
        request.setAttribute("employeeLastName", employee.getLastName());
        Long order_id = Long.parseLong(request.getParameter("order_id"));
        Boolean isAccepted = Boolean.parseBoolean(request.getParameter("bool"));
        employeeService.makeChanges(employee.getId(), isAccepted, order_id);
    }
}
