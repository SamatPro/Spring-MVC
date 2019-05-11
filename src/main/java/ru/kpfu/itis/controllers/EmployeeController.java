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
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.services.employee.EmployeeService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

  @Autowired
  @Qualifier(value = "employeeService")
  private EmployeeService employeeService;

  @RequestMapping(value = "/adminPage", method = RequestMethod.GET)
  public ModelAndView adminPage(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("employeePage");

    Employee employee = employeeService.getEmployee(request);

    modelAndView.addObject("employeeFirstName", employee.getFirstName());
    modelAndView.addObject("employeeLastName", employee.getLastName());
    return modelAndView;
  }

  @RequestMapping(value = "/orderProcessing", method = RequestMethod.GET)
  public ModelAndView orderProcesssing(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("orderProcessing");
    List<Order> orders = employeeService.findAllOrders();
    modelAndView.addObject("orders", orders);
    Employee employee = employeeService.getEmployee(request);

    modelAndView.addObject("employeeFirstName", employee.getFirstName());
    modelAndView.addObject("employeeLastName", employee.getLastName());
    return modelAndView;
  }
  @RequestMapping(value = "/order/check", method = RequestMethod.POST)
  public void checkOrder(HttpServletRequest request,
                                 ModelMap modelMap,
                                 @RequestParam(value = "order_id") Long order_id,
                                 @RequestParam(value = "bool") Boolean isAccepted){
    Employee employee = employeeService.getEmployee(request);

    modelMap.addAttribute("employeeFirstName", employee.getFirstName());
    modelMap.addAttribute("employeeLastName", employee.getLastName());
    employeeService.makeChanges(employee.getId(), isAccepted, order_id);
  }

}
