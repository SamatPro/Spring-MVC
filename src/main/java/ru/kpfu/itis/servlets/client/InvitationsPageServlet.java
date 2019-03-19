package ru.kpfu.itis.servlets.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.CooperativeTours;
import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.cooperativeTours.CooperativeToursRepository;
import ru.kpfu.itis.repositories.countries.CountriesRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@WebServlet("/myInvitations")
public class InvitationsPageServlet extends HttpServlet{

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = clientService.getClient(request);

        List<CooperativeTours> list = clientService.showUnConsentedTours(client.getId());
        request.setAttribute("coop", list);
        request.setAttribute("clientFirstName", client.getFirstName());
        request.setAttribute("clientLastName", client.getLastName());
        List<Country> countries = clientService.getCountries();
        request.setAttribute("countries", countries);

//        request.getRequestDispatcher("WEB-INF/jsp/client/invitationsPage.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/client/invitationsPage.ftl").forward(request, response);

    }
}
