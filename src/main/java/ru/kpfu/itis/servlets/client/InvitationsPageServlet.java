package ru.kpfu.itis.servlets.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.CooperativeTours;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/myInvitations")
public class InvitationsPageServlet extends HttpServlet{
    private OrdersRepository ordersRepository;
    private ClientService clientService;
    private ClientsRepository clientsRepository;
    private CooperativeToursRepository cooperativeToursRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.ordersRepository = (OrdersRepository) context.getAttribute("orderRepository");
        this.clientService = (ClientService) context.getAttribute("clientService");
        this.clientsRepository = (ClientsRepository) context.getAttribute("clientRepository");
        this.cooperativeToursRepository = (CooperativeToursRepository) context.getAttribute("cooperativeTours");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Client> clientOptional;
        Client client = null;
        Long clientId = null;

        Cookie cookies[] = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (clientService.isExistByCookie(cookie.getValue())) {
                        clientOptional = clientsRepository.findClientByCookie(cookie.getValue());
                        client = clientOptional.get();
                        clientId = client.getId();
                    }
                    else {
                        if (request.getSession().getAttribute("client")!=null) {
                            clientId = (Long) request.getSession().getAttribute("client");
                            clientOptional = clientsRepository.findOne(clientId);
                            client = clientOptional.get();
                        }
                    }
                }else {
                    if (request.getSession().getAttribute("client")!=null) {
                        clientId = (Long) request.getSession().getAttribute("client");
                        clientOptional = clientsRepository.findOne(clientId);
                        client = clientOptional.get();
                    }
                }
            }
        }

        else {
            if (request.getSession().getAttribute("client")!=null) {
                clientId = (Long) request.getSession().getAttribute("client");
                clientOptional = clientsRepository.findOne(clientId);
                client = clientOptional.get();
            }
        }
        List<CooperativeTours> list = cooperativeToursRepository.showUnConsentedTours(clientId);
        request.setAttribute("coop", list);
        request.setAttribute("clientFirstName", client.getFirstName());
        request.setAttribute("clientLastName", client.getLastName());

        request.getRequestDispatcher("WEB-INF/jsp/client/invitationsPage.jsp").forward(request, response);

    }
}
