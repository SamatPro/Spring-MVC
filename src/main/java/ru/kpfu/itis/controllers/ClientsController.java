package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.services.client.ClientService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ClientsController{

  @Autowired
  @Qualifier(value = "clientService")
  private ClientService clientService;

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String mainPage(){
    return "index";
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public String regPage(){
    return "reg";
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.POST)
  public String addUser(HttpServletRequest request){
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    System.out.println(password);
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    String middleName = request.getParameter("middle_name");
    String address = request.getParameter("address");
    Long phoneNumber  = Long.parseLong( request.getParameter("phone_number"));
    Boolean isMale = Boolean.parseBoolean(request.getParameter("gender"));
    Boolean newsSubscription = Boolean.parseBoolean(request.getParameter("subscription"));


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
    return "redirect:signIn";
  }

  @RequestMapping(value = "/signIn", method = RequestMethod.GET)
  public String signIn(){
    return "login";
  }

}
