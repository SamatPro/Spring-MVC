package ru.kpfu.itis.servlets.client;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.*;

//@WebServlet("/order/addToDB")
public class CooperUpdateServlet extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Client client = clientService.getClient(request);

        Long cityId = Long.parseLong(request.getParameter("cityId"));
        Long orderId = clientService.addOrder(client.getId(), cityId);

        String json = request.getParameter("json");
        ObjectMapper mapper = new ObjectMapper();
        List list = mapper.readValue(json, List.class);
        Long id;
        Iterator listIterator =  list.iterator();
        while (listIterator.hasNext()){
            String s = listIterator.next().toString().replace("{client=","");
            String newS = s.replace("}","");
            id = Long.parseLong(newS);
            clientService.addInvitation(orderId, id);
        }

    }
}