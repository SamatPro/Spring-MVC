package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.services.Service;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.services.employee.EmployeeService;
import ru.kpfu.itis.validator.LoginValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
  @Qualifier(value = "service")
  private Service service;

  @Autowired
  @Qualifier(value = "loginValidator")
  private LoginValidator loginValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(loginValidator);
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String mainPage(ModelMap modelMap){
    modelMap.addAttribute("pictures", service.getRandomPictures());
    return "index";
  }

  @RequestMapping(value = "/signIn", method = RequestMethod.GET)
  public String signIn(){
    return "login";
  }

  @RequestMapping(value = "/signIn", method = RequestMethod.POST)
  public String signIn(@Valid @ModelAttribute("loginForm") LoginForm loginForm,
                             BindingResult result,
                             RedirectAttributes attributes,
                             HttpServletResponse response){
    loginValidator.validate(loginForm, result);

    if (result.hasErrors()){
      attributes.addFlashAttribute("loginError", result.getAllErrors().get(0).getDefaultMessage());
      return "redirect:/signIn";
    }
    Optional<String> cookieValueCandidate = clientService.signIn(loginForm);
    Optional<String> cookieValueEmployee = employeeService.signIn(loginForm);
    if (cookieValueCandidate.isPresent()){
      Cookie authCookie = new Cookie("auth", cookieValueCandidate.get());
      authCookie.setMaxAge(60*60*24*30);
      response.addCookie(authCookie);
      return "redirect:/profilePage";
    }
    if (cookieValueEmployee.isPresent()){
        Cookie authCookie = new Cookie("auth", cookieValueEmployee.get());
        authCookie.setMaxAge(60*60*24*30);
        response.addCookie(authCookie);
        return "redirect:/adminPage";
    }

    return "redirect:/signIn";

  }

  @RequestMapping(value = "/signOut", method = RequestMethod.GET)
  public String signOut(HttpServletRequest request){
    Cookie cookies[] = request.getCookies();

    if(cookies != null){
      for (Cookie cookie : cookies){
        if(cookie.getName().equals("auth")){
          if (clientService.isExistByCookie(cookie.getValue())){
            clientService.deleteCookie(cookie.getValue());
            return "redirect:/signIn";
          }
          if (employeeService.isExistByCookie(cookie.getValue())){
            employeeService.deleteCookie(cookie.getValue());
            return "redirect:/signIn";
          }
        }
      }
    }else {
      return "redirect:/signIn";
    }

    HttpSession session = request.getSession();
    if (!session.isNew()){
      session.setAttribute("client", null);
      session.setAttribute("employee", null);
      return "redirect:/signIn";
    }
    return "redirect:/signIn";
  }



}
