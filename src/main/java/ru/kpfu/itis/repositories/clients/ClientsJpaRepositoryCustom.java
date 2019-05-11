package ru.kpfu.itis.repositories.clients;

import ru.kpfu.itis.models.Client;

import java.util.Optional;

public interface ClientsJpaRepositoryCustom {
  Optional<Client> findClientByCookie(String cookieValue);
}
