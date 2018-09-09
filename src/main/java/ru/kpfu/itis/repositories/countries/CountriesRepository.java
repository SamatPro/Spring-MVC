package ru.kpfu.itis.repositories.countries;

import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;

public interface CountriesRepository extends CrudRepository<Country> {
    @Override
    List<Country> findAll();
}
