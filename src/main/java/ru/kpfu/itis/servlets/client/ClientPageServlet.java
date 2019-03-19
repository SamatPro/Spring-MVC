package ru.kpfu.itis.servlets.client;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

//@WebServlet("/profilePage")
public class ClientPageServlet extends HttpServlet {

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

        List<Country> countries = clientService.getCountries();
        request.setAttribute("countries", countries);
//        request.getRequestDispatcher("WEB-INF/jsp/client/profilePage.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/client/profilePage.ftl").forward(request, response);

    }

}
