package ru.kpfu.itis.servlets;

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
import java.util.Optional;

@WebServlet("/order/update")
public class OrderUpdateServlet extends HttpServlet {
    OrdersRepository ordersRepository;
    ClientService clientService;
    ClientsRepository clientsRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ordersRepository = (OrdersRepository) context.getAttribute("orderRepository");
        clientService = (ClientService) context.getAttribute("clientService");
        clientsRepository = (ClientsRepository) context.getAttribute("clientRepository");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long client_id = null; /*= Long.parseLong(req.getParameter("client_id"));*/
        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())){
                        Optional<Client> clientOptional = clientsRepository.findClientByCookie(cookie.getValue());
                        Client client = clientOptional.get();
                        client_id = client.getId();
                    }
                }
            }
        }
        Long city_id = Long.parseLong(request.getParameter("city_id"));
        ordersRepository.addOrder(client_id, city_id);

    }
}
