package ru.kpfu.itis.repositories;

import java.util.List;

public interface CrudRepository<T> {
    T findOne(Long id);
    void save(T model);
    void delete(Long id);

    List<T> findAll();

}
