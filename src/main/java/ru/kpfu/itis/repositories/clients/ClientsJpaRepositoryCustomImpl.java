package ru.kpfu.itis.repositories.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Client;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Component("ClientsJpaRepository")
public class ClientsJpaRepositoryCustomImpl implements ClientsJpaRepositoryCustom {
  @Autowired
  EntityManager em;

  @Override
  public Optional<Client> findClientByCookie(String cookieValue) {
    return Optional.of((Client) em.createQuery("SELECT c FROM Client c WHERE c.auth.cookieValue=:cookieValue").setParameter("cookieValue", cookieValue).getSingleResult());
  }
}
