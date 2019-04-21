package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//@Controller
public class EmployeeController {

  @Autowired
  @Qualifier(value = "employeeService")
  private EmployeeService employeeService;

  @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
  public ModelAndView profilePage(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("profilePage");

    Employee employee = employeeService.getEmployee(request);

    modelAndView.addObject("employeeFirstName", employee.getFirstName());
    modelAndView.addObject("employeeLastName", employee.getLastName());
    return modelAndView;
  }

//  @RequestMapping(value = "/signIn", method = RequestMethod.GET)
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
    Optional<String> cookieValueCandidate = employeeService.signIn(loginForm);
    Employee employee = employeeService.getEmployeeByCookie(cookieValueCandidate.get());
    Cookie authCookie = new Cookie("auth", cookieValueCandidate.get());
    authCookie.setMaxAge(60*60*24*30);
    response.addCookie(authCookie);
    System.out.println(employee.getFirstName());
    return "redirect:adminPage";
  }
}
