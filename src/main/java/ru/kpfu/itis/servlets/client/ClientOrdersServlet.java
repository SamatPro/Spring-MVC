package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
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

@WebServlet("/orders")
public class ClientOrdersServlet extends HttpServlet {

    ClientService clientService;
    ClientsRepository clientsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
        this.clientsRepository = (ClientsRepository) context.getAttribute("clientRepository");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();
        Long client_id = null;
        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())){
                        Optional<Client> clientOptional = clientsRepository.findClientByCookie(cookie.getValue());
                        Client client = clientOptional.get();
                        client_id = client.getId();
                        request.setAttribute("clientFirstName", client.getFirstName());
                        request.setAttribute("clientLastName", client.getLastName());

                    }
                }
            }
        }
        List<Order> orders = clientsRepository.findOrdersOfClientById(client_id);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("jsp/ordersPage.jsp").forward(request, response);
    }
}
