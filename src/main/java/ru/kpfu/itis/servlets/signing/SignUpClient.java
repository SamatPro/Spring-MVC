package ru.kpfu.itis.servlets.signing;

import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.repositories.cities.CitiesRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.client.ClientServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebServlet(name = "signUpClient", value = "/signUp")
public class SignUpClient extends HttpServlet {

    private ClientService clientService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.clientService = (ClientService) context.getAttribute("clientService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("WEB-INF/jsp/reg.jsp").forward(request, response);
        request.getRequestDispatcher("WEB-INF/templates/reg.ftl").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println(password);
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String middleName = request.getParameter("middle_name");
        String address = request.getParameter("address");
        Long phoneNumber = null;
        if(request.getParameter("phone_number").length()>0){
            phoneNumber = Long.parseLong( request.getParameter("phone_number"));
        }
        Boolean isMale = Boolean.parseBoolean(request.getParameter("gender"));
        Boolean newsSubscription = Boolean.parseBoolean(request.getParameter("subscription"));

        Boolean consent = Boolean.parseBoolean(request.getParameter("consent"));
        Boolean repass = password.equals(request.getParameter("repassword"));

        if(consent
            && repass
            && password.length()>4
            && email.length()>0
            && firstName.length()>0
            && lastName.length()>0
            && middleName.length()>0
            && address.length()>0
            && isMale != null
            && phoneNumber != null) {

            if (clientService.emailDoesntExist(email)) {
                ClientForm clientForm = ClientForm.builder()
                        .email(email)
                        .password(password)
                        .firstName(firstName)
                        .lastName(lastName)
                        .middleName(middleName)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .isMale(isMale)
                        .newsSubscription(newsSubscription)
                        .build();
                clientService.signUp(clientForm);
                response.setContentType("text/html");
                response.sendRedirect("/signIn");
            }else{
                request.setAttribute("existingEmailError", "User with this email already exists.");
//                request.getRequestDispatcher("WEB-INF/jsp/reg.jsp").forward(request, response);
                request.getRequestDispatcher("WEB-INF/templates/reg.ftl").forward(request, response);

            }
        } else {
            if (!consent)
                request.setAttribute("consentError", "You must agree to consent to the processing of personal data");
            if (!(firstName.length() > 0 && lastName.length() > 0 && middleName.length() > 0 && isMale != null && address.length() > 0)) {
                request.setAttribute("fieldsError", "Name, gender or address fields are empty");
            }
            if (email!=null) {
                request.setAttribute("email", email);
            }
            if (password!=null){
                request.setAttribute("password", password);
            }
            if (repass && password.length()>4){
                request.setAttribute("repass", password);
            }
            if (phoneNumber!=null){
                request.setAttribute("phoneNum", phoneNumber);
            }
            if (address!=null){
                request.setAttribute("address", address);
            }
            if (lastName!=null){
                request.setAttribute("lastName", lastName);
            }
            if (firstName!=null){
                request.setAttribute("firstName", firstName);
            }
            if (middleName!=null){
                request.setAttribute("middleName", middleName);
            }
            if (!repass) {
                request.setAttribute("passwordError", "Passwords do not match");
            }
//            request.getRequestDispatcher("WEB-INF/jsp/reg.jsp").forward(request, response);
            request.getRequestDispatcher("WEB-INF/templates/reg.ftl").forward(request, response);
            return;

        }

    }
}
