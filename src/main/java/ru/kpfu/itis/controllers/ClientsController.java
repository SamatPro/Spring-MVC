package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.models.*;
import ru.kpfu.itis.services.Service;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.validator.ClientValidator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
public class ClientsController{

  @Autowired
  @Qualifier(value = "clientService")
  private ClientService clientService;

  @Autowired
  @Qualifier(value = "service")
  private Service service;

  @Autowired
  @Qualifier(value = "clientValidator")
  private ClientValidator clientValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(clientValidator);
  }


  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public String regPage(ModelMap model){
    model.addAttribute("clientForm", new ClientForm());
    return "reg";
  }

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String getUsersPage(ModelMap modelMap) {
    List<Client> clients = clientService.findAllByFirstName("%");
    modelMap.addAttribute("clients", clients);
    return "user_page";
  }

  @RequestMapping(value = "/profilePage", method = RequestMethod.GET)
  public ModelAndView profilePage(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("profilePage");

    Client client = clientService.getClient(request);
    modelAndView.addObject("pictures", service.getRandomPictures());

    modelAndView.addObject("clientFirstName", client.getFirstName());
    modelAndView.addObject("clientLastName", client.getLastName());
    modelAndView.addObject("countries", clientService.getCountries());
    return modelAndView;
  }

  @RequestMapping(value = "saveClient", method = RequestMethod.POST)
  public String addUser(@Valid @ModelAttribute("clientForm")
                          ClientForm clientForm,
                        BindingResult result,
                        RedirectAttributes attributes,
                        Model model){
    clientValidator.validate(clientForm, result);
    if (result.hasErrors()) {
      attributes.addFlashAttribute("error", result.getAllErrors());
      return "redirect:signUp";
    }
    clientService.signUp(clientForm);

    attributes.addFlashAttribute("login", clientForm.getEmail());
    attributes.addFlashAttribute("password", clientForm.getPassword());
    return "redirect:signIn";
  }

  @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Client> getUsersAsJson() {
    return clientService.findAllByFirstName("%");
  }


  @RequestMapping(value = "/myInvitations", method = RequestMethod.GET)
  public String invitationsPage(HttpServletRequest request, ModelMap modelMap){
    Client client = clientService.getClient(request);

    List<CooperativeTour> list = clientService.showUnConsentedTours(client.getId());
    modelMap.addAttribute("coop", list);
    modelMap.addAttribute("clientFirstName", client.getFirstName());
    modelMap.addAttribute("clientLastName", client.getLastName());

    List<Country> countries = clientService.getCountries();
    modelMap.addAttribute("countries", countries);

    return "invitationsPage";
  }

  @RequestMapping(value = "/orders", method = RequestMethod.GET)
  public String ordersPage(HttpServletRequest request, ModelMap modelMap){
    Client client = clientService.getClient(request);

    modelMap.addAttribute("clientFirstName", client.getFirstName());
    modelMap.addAttribute("clientLastName", client.getLastName());
    List<Order> orders = clientService.findOrdersOfClientById(client.getId());
    List<Country> countries = clientService.getCountries();
    modelMap.addAttribute("countries", countries);
    modelMap.addAttribute("orders", orders);

    return "ordersPage";
  }


  @RequestMapping(value = "/tours", method = RequestMethod.GET)
  public String toursPage(
          HttpServletRequest request,
          @RequestParam(value = "country") String country,
          ModelMap modelMap
  ){
    Client client = clientService.getClient(request);
    modelMap.addAttribute("clientFirstName", client.getFirstName());
    modelMap.addAttribute("clientLastName", client.getLastName());

    List<Country> countries = clientService.getCountries();
    modelMap.addAttribute("countries", countries);

    List<City> cities;

    List<Picture> pictures;

    cities = clientService.getCities(country);

    pictures = clientService.getPictures(country);

    modelMap.addAttribute("cityList", cities);
    modelMap.addAttribute("picture", pictures);
    modelMap.addAttribute("country", country);
    return "toursPage";
  }

  @RequestMapping(value = "/order/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<Client> getUsersAsJson(
          @RequestParam(value = "name") String name
  ) {
    List<Client> clients = clientService.findAllByFirstName(name);
    return clients;
  }

  @RequestMapping(value = "/order/update", method = RequestMethod.POST)
  @ResponseBody
  public void updateOrder(
          HttpServletRequest request,
          @RequestParam(value = "city_id") Long city_id
    ) {
    Client client = clientService.getClient(request);
    clientService.addOrder(client.getId(), city_id);
  }

  @RequestMapping(value = "/order/delete", method = RequestMethod.POST)
  @ResponseBody
  public void deleteOrder(
          HttpServletRequest request,
          @RequestParam(value = "del_order") Long order_id
  ) {
    clientService.deleteOrder(order_id);
  }


  @RequestMapping(value = "/order/consent", method = RequestMethod.POST)
  @ResponseBody
  public void consentOrder(
          @RequestParam(value = "bool") Boolean bool,
          @RequestParam(value = "id") Long id
  ) {
    if (bool){
      clientService.updateConsent(id);
    }else {
      clientService.deleteConsent(id);
    }
  }

  @RequestMapping(value = "/order/addToDB", method = RequestMethod.POST)
  @ResponseBody
  public void consentOrder(HttpServletRequest request,
          @RequestParam(value = "cityId") Long cityId
  ) throws IOException {


    Client client = clientService.getClient(request);

    Long orderId = clientService.addOrder(client.getId(), cityId);

    String json = request.getParameter("json");
    ObjectMapper mapper = new ObjectMapper();
    List list = mapper.readValue(json, List.class);
    Long id;
    Iterator listIterator =  list.iterator();
    while (listIterator.hasNext()){
      String s = listIterator.next().toString().replace("{client=","");
      String newS = s.replace("}","");
      id = Long.parseLong(newS);
      clientService.addInvitation(orderId, id);
    }

  }

  @RequestMapping(value = "/profilePage", method = RequestMethod.POST)
  public String addReview(HttpServletRequest request,
                           @RequestParam(value = "reviewText") String reviewText
  ) {

    Client client = clientService.getClient(request);
    clientService.addReview(client.getId(), reviewText);
    return "redirect:/profilePage";
  }
  @RequestMapping(value = "/reviews", method = RequestMethod.GET)
  public String reviews(ModelMap modelMap) {
    modelMap.addAttribute("reviews", service.getAllReviews());
    return "reviews";
  }



}
