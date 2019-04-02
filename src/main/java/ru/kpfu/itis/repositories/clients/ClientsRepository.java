package ru.kpfu.itis.repositories.clients;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Order;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientsRepository extends CrudRepository<Client> {
    List<Client> findAllByFirstName(String firstName);
    Optional<Client> findOneByEmail(String email);
    Optional<Client> findClientByCookie(String cookieValue);
    List<Client> findAllOnlyWithName();
}
