package ru.kpfu.itis.repositories;

import ru.kpfu.itis.models.Auth;
import ru.kpfu.itis.models.Client;

import java.util.Optional;

public interface AuthRepository extends CrudRepository<Auth>{
    Optional<Auth> findByCookieValue(String cookieValue);
    Optional<Auth> findByCookieValueEmployee(String cookieValue);
    Optional<Client> findClientByCookie(String cookieValue);
    void deleteCookie(String cookieValue);
    void saveEmployee(Auth employee);
}
