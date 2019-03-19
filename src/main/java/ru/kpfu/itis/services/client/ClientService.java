package ru.kpfu.itis.services.client;

import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.EmployeeForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ClientService {
    void signUp(ClientForm clientForm);
    Optional<String> signIn(LoginForm loginForm);
    Long signInBySession(LoginForm loginForm);
    boolean isExistByCookie(String cookieValue);
    Client getClient(HttpServletRequest request);
    List<Country> getCountries();
    Long addOrder(Long clientId, Long cityId);
    void addInvitation(Long orderId, Long id);
    List<Order> findOrdersOfClientById(Long id);
    List<CooperativeTours> showUnConsentedTours(Long id);
    void updateConsent(Long id);
    void deleteConsent(Long id);
    void deleteOrder(Long id);
    List<Client> findAllByFirstName(String name);
    Client getClientByCookie(String cookie);
    boolean emailDoesntExist(String email);
    void deleteCookie(String cookie);
    List<City> getCities(String countryName);
    List<Picture> getPictures(String countryName);

}
