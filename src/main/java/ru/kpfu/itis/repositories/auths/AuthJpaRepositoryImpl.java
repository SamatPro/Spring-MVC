package ru.kpfu.itis.repositories.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.Auth;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Component("authJpaRepository")
public class AuthJpaRepositoryImpl implements AuthJpaRepository {

  @Autowired
  EntityManager em;

  @Override
  public Optional<Auth> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  @Transactional
  public void save(Auth model) {
    em.merge(model);
  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void update(Auth model) {

  }

  @Override
  public List<Auth> findAll() {
    return null;
  }
}
