package ru.kpfu.itis.servlets.client;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

//@WebServlet("/order/update")
public class OrderUpdateServlet extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientService.getClient(request);
        Long city_id = Long.parseLong(request.getParameter("city_id"));
        clientService.addOrder(client.getId(), city_id);
    }
}
