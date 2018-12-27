package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.repositories.employees.EmployeesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
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
@WebServlet("/order/check")
public class OrderCheckServlet  extends HttpServlet {
    OrdersRepository ordersRepository;
    EmployeeService employeeService;
    EmployeesRepository employeesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ordersRepository = (OrdersRepository) context.getAttribute("orderRepository");
        employeeService = (EmployeeService) context.getAttribute("employeeService");
        employeesRepository = (EmployeesRepository) context.getAttribute("employeeRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employee_id = null;

        Cookie cookies[] = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (employeeService.isExistByCookie(cookie.getValue())){
                        Optional<Employee> employeeOptional = employeesRepository.findEmployeeByCookie(cookie.getValue());
                        Employee employee = employeeOptional.get();
                        employee_id = employee.getId();
                    }
                }
            }
        }
        Long order_id = Long.parseLong(request.getParameter("order_id"));
        Boolean isAccepted = Boolean.parseBoolean(request.getParameter("bool"));
        ordersRepository.makeChanges(employee_id, isAccepted, order_id);
    }
}
