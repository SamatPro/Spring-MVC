package ru.kpfu.itis.servlets.admin;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.ToString;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
//@WebServlet(name = "orderProcessing", value = "/orderProcessing")
public class OrderProcessingServlet extends HttpServlet {

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

        List<Order> orders = employeeService.findAllOrders();
        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("WEB-INF/jsp/employee/orderProcessing.jsp").forward(request,response);
        request.getRequestDispatcher("/WEB-INF/templates/employee/orderProcessing.ftl").forward(request,response);

    }
}
