package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Picture;
import ru.kpfu.itis.repositories.cities.CitiesRepository;
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
@WebServlet("/tours")
public class ToursOrderServlet extends HttpServlet {

    ClientService clientService;
    ClientsRepository clientsRepository;
    CitiesRepository citiesRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
        this.clientsRepository = (ClientsRepository) context.getAttribute("clientRepository");
        this.citiesRepository = (CitiesRepository) context.getAttribute("cityRepository");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())){
                        Optional<Client> clientOptional = clientsRepository.findClientByCookie(cookie.getValue());
                        Client client = clientOptional.get();
                        request.setAttribute("clientId", client.getId());
                        request.setAttribute("clientFirstName", client.getFirstName());
                        request.setAttribute("clientLastName", client.getLastName());
                    }
                }
            }
        }

        List<City> cities = citiesRepository.findCitiesByCountryId(Long.parseLong("1"));
        List<Picture> pictures = citiesRepository.findAllPicturesByCountryId(Long.parseLong("1"));

        request.setAttribute("cityList", cities);
        request.setAttribute("picture", pictures);
        request.setAttribute("country", "Туры по России");
        request.getRequestDispatcher("jsp/europeTours.jsp").forward(request, response);
    }

}
