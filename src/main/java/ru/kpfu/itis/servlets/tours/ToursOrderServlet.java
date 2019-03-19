package ru.kpfu.itis.servlets.tours;

import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.models.Picture;
import ru.kpfu.itis.repositories.cities.CitiesRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.countries.CountriesRepository;
import ru.kpfu.itis.repositories.pictures.PicturesRepository;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
//@WebServlet("/tours")
public class ToursOrderServlet extends HttpServlet {

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

        List<City> cities;
        List<Picture> pictures;

        String countryName = request.getParameter("country");
        cities = clientService.getCities(countryName);
        pictures = clientService.getPictures(countryName);
        request.setAttribute("cityList", cities);
        request.setAttribute("picture", pictures);
        request.setAttribute("country", countryName);

//        request.getRequestDispatcher("WEB-INF/jsp/tours/toursPage.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/tours/toursPage.ftl").forward(request, response);

    }

}
