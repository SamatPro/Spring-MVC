package ru.kpfu.itis.servlets.client;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.countries.CountriesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

//@WebServlet("/orders")
public class ClientOrdersServlet extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientService.getClient(request);

        request.setAttribute("clientFirstName", client.getFirstName());
        request.setAttribute("clientLastName", client.getLastName());
        List<Order> orders = clientService.findOrdersOfClientById(client.getId());
        List<Country> countries = clientService.getCountries();
        request.setAttribute("countries", countries);
        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("WEB-INF/jsp/client/ordersPage.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/client/ordersPage.ftl").forward(request, response);

    }
}
