package ru.kpfu.itis.servlets.client;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.cooperativeTours.CooperativeToursRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.services.client.ClientService;

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
//@WebServlet("/order/consent")
public class OrderConsent extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientService.getClient(request);

        if (Boolean.parseBoolean(request.getParameter("bool"))){
            clientService.updateConsent(Long.parseLong(request.getParameter("id")));
        }else {
            clientService.deleteConsent(Long.parseLong(request.getParameter("id")));
        }
    }
}