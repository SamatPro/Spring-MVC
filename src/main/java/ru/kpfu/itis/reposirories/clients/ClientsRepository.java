package ru.kpfu.itis.reposirories.clients;

import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.reposirories.CrudRepository;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Client> {
    List<Client> findAllByFirstName(String firstName);
    List<Client> findAllByGender(String gender);
    List<Client> findAllByAge(Long age);
}
