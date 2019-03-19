package ru.kpfu.itis.servlets;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

//@WebServlet("/home")
public class MainServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Запрос........................" + request.getRemoteAddr());
        request.getRequestDispatcher("WEB-INF/templates/index.ftl").forward(request, response);

    }
}
