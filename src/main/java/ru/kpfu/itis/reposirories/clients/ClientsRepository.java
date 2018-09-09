package ru.kpfu.itis.reposirories.clients;

import ru.kpfu.itis.mappers.Client;
import ru.kpfu.itis.reposirories.CrudRepository;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Client> {
    List<Client> findAllByFirstName(String firstName);
}
