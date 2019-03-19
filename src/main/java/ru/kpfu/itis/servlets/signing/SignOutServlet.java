package ru.kpfu.itis.servlets.signing;

import ru.kpfu.itis.repositories.auths.AuthRepository;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

//@WebServlet("/signOut")
public class SignOutServlet extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookies[] = request.getCookies();

        if(cookies != null){
            for (Cookie cookie : cookies){
                if(cookie.getName().equals("auth")){
                    if (clientService.isExistByCookie(cookie.getValue())){
                        clientService.deleteCookie(cookie.getValue());
                        response.sendRedirect("/signIn");
                        return;
                    }
                }
            }
        }else {
            response.sendRedirect("/signIn");
            return;
        }

        HttpSession session = request.getSession();
        if (!session.isNew()){
//            session.invalidate();
            session.setAttribute("client", null);
            session.setAttribute("employee", null);
            response.sendRedirect("/signIn");
            return;
        }
    }
}
