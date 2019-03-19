package ru.kpfu.itis.services.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.forms.ClientForm;
import ru.kpfu.itis.forms.LoginForm;
import ru.kpfu.itis.models.*;
import ru.kpfu.itis.repositories.auths.AuthRepository;
import ru.kpfu.itis.repositories.cities.CitiesRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.cooperativeTours.CooperativeToursRepository;
import ru.kpfu.itis.repositories.countries.CountriesRepository;
import ru.kpfu.itis.repositories.orders.OrdersRepository;
import ru.kpfu.itis.repositories.pictures.PicturesRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("clientService")
public class ClientServiceImpl implements ClientService {

    @Autowired
    @Qualifier("clientsRepositoryJdbcTemplateImpl")
    private ClientsRepository clientsRepository;
    @Autowired
    @Qualifier("authRepositoryJdbcTemplateImpl")
    private AuthRepository authRepository;
    @Autowired
    @Qualifier("countriesRepositoryJdbcTemplateImpl")
    private CountriesRepository countriesRepository;
    @Autowired
    @Qualifier("ordersRepositoryJdbcTemplateImpl")
    private OrdersRepository ordersRepository;
    @Autowired
    @Qualifier("citiesRepositoryJdbcTemplateImpl")
    private CitiesRepository citiesRepository;
    @Autowired
    @Qualifier("cooperativeToursRepositoryJdbcTemplateImpl")
    private CooperativeToursRepository cooperativeToursRepository;
    @Autowired
    @Qualifier("picturesRepositoryJdbcTemplateImpl")
    private PicturesRepository picturesRepository;
//    @Autowired
    private PasswordEncoder encoder;

    public ClientServiceImpl() {
//        context = new
//                AnnotationConfigApplicationContext(JavaConfig.class);

//        this.clientsRepository = context.getBean(ClientsRepository.class);
//        this.authRepository = context.getBean(AuthRepository.class);
//        this.countriesRepository = context.getBean(CountriesRepository.class);
//        this.ordersRepository = context.getBean(OrdersRepository.class);
//        this.citiesRepository = context.getBean(CitiesRepository.class);
//        this.cooperativeToursRepository = context.getBean(CooperativeToursRepository.class);
//        this.picturesRepository = context.getBean(PicturesRepository.class);

        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(ClientForm clientForm) {
        Client client = Client.builder()
                .lastName(clientForm.getLastName())
                .firstName(clientForm.getFirstName())
                .middleName(clientForm.getMiddleName())
                .email(clientForm.getEmail())
                .hashPassword(encoder.encode(clientForm.getPassword()))
                .phoneNumber(clientForm.getPhoneNumber())
                .address(clientForm.getAddress())
                .isMale(clientForm.getIsMale())
                .newsSubscription(clientForm.getNewsSubscription())
                .build();
        clientsRepository.save(client);
    }

    @Override
    public Optional<String> signIn(LoginForm loginForm) {
        Optional<Client> clientOptional = clientsRepository.findOneByEmail(loginForm.getEmail());
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            if(encoder.matches(loginForm.getPassword(), client.getHashPassword())){
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = Auth.builder()
                        .client(client)
                        .cookieValue(cookieValue)
                        .build();
                authRepository.save(auth);
                return Optional.of(cookieValue);

//                return Optional.of(client.getId().toString());

            }

        }
        return Optional.empty();
    }

    @Override
    public Long signInBySession(LoginForm loginForm) {
        Optional<Client> clientOptional = clientsRepository.findOneByEmail(loginForm.getEmail());
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            if(encoder.matches(loginForm.getPassword(), client.getHashPassword())){
                return client.getId();
            }

        }
        return null;
    }

    @Override
    public boolean isExistByCookie(String cookieValue) {
        if(!authRepository.findByCookieValue(cookieValue).equals(Optional.empty())){
            return true;
        }
        return false;
    }

    @Override
    public Client getClient(HttpServletRequest request) {
        Optional<Client> clientOptional;
        Client client = null;
        Long clientId = null;

        Cookie cookies[] = request.getCookies();

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    if (isExistByCookie(cookie.getValue())) {
                        clientOptional = clientsRepository.findClientByCookie(cookie.getValue());
                        client = clientOptional.get();
                    }
                    else {
                        if (request.getSession().getAttribute("client")!=null) {
                            clientId = (Long) request.getSession().getAttribute("client");
                            clientOptional = clientsRepository.findOne(clientId);
                            client = clientOptional.get();
                        }
                    }
                }else {
                    if (request.getSession().getAttribute("client")!=null) {
                        clientId = (Long) request.getSession().getAttribute("client");
                        clientOptional = clientsRepository.findOne(clientId);
                        client = clientOptional.get();
                    }
                }
            }
        }

        else {
            if (request.getSession().getAttribute("client")!=null) {
                clientId = (Long) request.getSession().getAttribute("client");
                clientOptional = clientsRepository.findOne(clientId);
                client = clientOptional.get();
            }
        }
        return client;
    }

    @Override
    public List<Country> getCountries() {
        return countriesRepository.findAll();
    }

    @Override
    public Long addOrder(Long clientId, Long cityId) {
        return ordersRepository.addOrderForCoop(clientId, cityId);
    }

    @Override
    public void addInvitation(Long orderId, Long id) {
        cooperativeToursRepository.addInvitation(orderId, id);
    }

    @Override
    public List<Order> findOrdersOfClientById(Long id) {
        return ordersRepository.findOrdersOfClientById(id);
    }

    @Override
    public List<CooperativeTours> showUnConsentedTours(Long id) {
        return cooperativeToursRepository.showUnConsentedTours(id);
    }

    @Override
    public void updateConsent(Long id) {
        cooperativeToursRepository.updateConsent(id);
    }

    @Override
    public void deleteConsent(Long id) {
        cooperativeToursRepository.deleteConsent(id);
    }

    @Override
    public void deleteOrder(Long id) {
        cooperativeToursRepository.delete(id);
        ordersRepository.delete(id);
    }

    @Override
    public List<Client> findAllByFirstName(String name) {
        return clientsRepository.findAllByFirstName(name);
    }

    @Override
    public Client getClientByCookie(String cookie) {
        return clientsRepository.findClientByCookie(cookie).get();
    }

    @Override
    public boolean emailDoesntExist(String email) {
        return !clientsRepository.findOneByEmail(email).isPresent();
    }

    @Override
    public void deleteCookie(String cookie) {
        authRepository.deleteCookie(cookie);
    }

    @Override
    public List<City> getCities(String countryName) {
        return citiesRepository.findCitiesByCountryName(countryName);
    }

    @Override
    public List<Picture> getPictures(String countryName) {
        return picturesRepository.findAllPicturesByCountryName(countryName);
    }
}
