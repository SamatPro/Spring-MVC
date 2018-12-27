package ru.kpfu.itis.servlets;

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
@ToString
@WebServlet("/orderProcessing")
public class OrderProcessingServlet extends HttpServlet {
    OrdersRepository ordersRepository;
    EmployeesRepository employeesRepository;
    EmployeeService employeeService;
    ClientsRepository clientsRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ordersRepository = (OrdersRepository) context.getAttribute("orderRepository");
        employeeService = (EmployeeService) context.getAttribute("employeeService");
        employeesRepository = (EmployeesRepository) context.getAttribute("employeeRepository");
        clientsRepository = (ClientsRepository) context.getAttribute("clientRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employee_id = null;

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
//        List<Order> client = clientsRepository.findAll().iterator().next().getOrderList();
//        Iterator<Client> clientIterator = clients.iterator();
//        request.setAttribute("client", client);
        /*while (clientIterator.hasNext()){
            request.setAttribute(clientIterator.next());
        }*/
        List<Order> orders = ordersRepository.findAll();
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/jsp/orderProcessing.jsp").forward(request,response);
    }
}
