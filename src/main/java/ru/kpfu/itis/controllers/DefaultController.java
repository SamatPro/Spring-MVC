package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;
import ru.kpfu.itis.validator.LoginValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

@Controller
public class DefaultController {

  @Autowired
  @Qualifier(value = "clientService")
  private ClientService clientService;

  @Autowired
  @Qualifier(value = "employeeService")
  private EmployeeService employeeService;

  @Autowired
  @Qualifier(value = "loginValidator")
  private LoginValidator loginValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(loginValidator);
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String mainPage(){
    return "index";
  }

  /*@RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public String regPage(ModelMap model){
    model.addAttribute("clientForm", new ClientForm());
    return "reg";
  }*/

  @RequestMapping(value = "/signIn", method = RequestMethod.GET)
  public String signIn(){
    return "login";
  }

//  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  public String signIn(
//          @CookieValue(value = "auth") String cookie,
          @RequestParam(value = "email") String email,
          @RequestParam(value = "password") String password,
          @RequestParam(value = "remember", required = false) Boolean remember,
          ModelMap model,
          HttpServletResponse response
  ) throws IOException {
    LoginForm loginForm = LoginForm.builder()
            .email(email)
            .password(password)
            .build();
    Optional<String> cookieValueCandidate = clientService.signIn(loginForm);
    Client client = clientService.getClientByCookie(cookieValueCandidate.get());
    Cookie authCookie = new Cookie("auth", cookieValueCandidate.get());
    authCookie.setMaxAge(60*60*24*30);
    response.addCookie(authCookie);
    System.out.println(client.getFirstName());
    return "redirect:profilePage";
  }
  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  public String signIn(@Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result,
                       RedirectAttributes attributes,
                       HttpServletResponse response,
                       Model model){

    loginValidator.validate(loginForm, result);
    if (result.hasErrors()){
      attributes.addFlashAttribute("loginError", result.getAllErrors().get(0).getDefaultMessage());
      return "redirect:signIn";
    }
    Optional<String> cookieValueCandidate = clientService.signIn(loginForm);
    Client client = clientService.getClientByCookie(cookieValueCandidate.get());
    Cookie authCookie = new Cookie("auth", cookieValueCandidate.get());
    authCookie.setMaxAge(60*60*24*30);
    response.addCookie(authCookie);
    return "redirect:profilePage";
  }



}
