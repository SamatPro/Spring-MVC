package ru.kpfu.itis.servlets.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
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
import java.util.List;
import java.util.Optional;
//@WebServlet("/order/search")
public class SearchServlet extends HttpServlet {

    private ClientService clientService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        List<Client> clients = clientService.findAllByFirstName(name);
        String json = objectMapper.writeValueAsString(clients);
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(json);

    }
}
