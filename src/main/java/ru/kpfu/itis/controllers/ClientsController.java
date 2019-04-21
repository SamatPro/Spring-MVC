package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.*;
import ru.kpfu.itis.services.client.ClientService;
import ru.kpfu.itis.validator.ClientValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientsController{

  @Autowired
  @Qualifier(value = "clientService")
  private ClientService clientService;

  @Autowired
  @Qualifier(value = "clientValidator")
  private ClientValidator clientValidator;

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.addValidators(clientValidator);
  }



//  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String mainPage(){

    return "index";
  }

  @RequestMapping(value = "/signUp", method = RequestMethod.GET)
  public String regPage(ModelMap model){
    model.addAttribute("clientForm", new ClientForm());
    return "reg";
  }

//  @RequestMapping(value = "saveClient", method = RequestMethod.POST)
  public String addUser(
          @RequestParam(value = "email", defaultValue = "") String email,
          @RequestParam(value = "password", defaultValue = "") String password,
          @RequestParam(value = "repassword", defaultValue = "") String repass,
          @RequestParam(value = "first_name", defaultValue = "") String firstName,
          @RequestParam(value = "last_name", defaultValue = "") String lastName,
          @RequestParam(value = "middle_name", defaultValue = "") String middleName,
          @RequestParam(value = "address", defaultValue = "") String address,
          @RequestParam(value = "phone_number", defaultValue = "") Long phoneNumber,
          @RequestParam(value = "gender", defaultValue = "") Boolean isMale,
          @RequestParam(value = "subscription", defaultValue="false") Boolean newsSubscription,
          @RequestParam(value = "consent", defaultValue="false") Boolean consent,
          ModelMap model
          ){

    if(consent
            && repass.equals(password)
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
        return "redirect:signIn";
      }else{
        model.addAttribute("signUpError", "Пользователь с таким логином уже существует.");
        return "signUp";

      }
    } else {
      if (!consent)
        model.addAttribute("signUpError", "Согласие на обработку данных!");
      if (!(firstName.length() > 0 && lastName.length() > 0 && middleName.length() > 0 && isMale != null && address.length() > 0)) {
        model.addAttribute("signUpError", "Name, gender or address fields are empty");
      }
      if (email!=null) {
        model.addAttribute("signUpError", "Email");
      }
      if (password!=null){
        model.addAttribute("signUpError", "Password");
      }
      /*if (repass && password.length()>4){
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
      }*/

      return "reg";

    }
  }

  /*@RequestMapping(value = "/signIn", method = RequestMethod.GET)
  public String signIn(){
    return "login";
  }*/

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String getUsersPage(ModelMap modelMap) {
    List<Client> clients = clientService.findAllByFirstName("%");
    modelMap.addAttribute("clients", clients);
    return "user_page";
  }

  /*@RequestMapping(value = "/signIn", method = RequestMethod.POST)
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
  }*/

  @RequestMapping(value = "/profilePage", method = RequestMethod.GET)
  public ModelAndView profilePage(HttpServletRequest request){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("profilePage");

    Client client = clientService.getClient(request);
    System.out.println(client.getFirstName());

    modelAndView.addObject("clientFirstName", client.getFirstName());
    modelAndView.addObject("clientLastName", client.getLastName());
    modelAndView.addObject("countries", clientService.getCountries());
    return modelAndView;
  }

  /*@RequestMapping(value = "saveClient", method = RequestMethod.POST)
  public String addUser(@ModelAttribute("clientForm") @Validated ClientForm clientForm,
          BindingResult result, Model model){

    clientService.signUp(clientForm);
    return "redirect:signIn";
  }*/
 // проверить название

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

    List<CooperativeTours> list = clientService.showUnConsentedTours(client.getId());
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









}
