package ru.kpfu.itis.reposirories.countries;

import ru.kpfu.itis.models.Country;
import ru.kpfu.itis.reposirories.CrudRepository;

import java.util.List;

public interface CountriesRepository extends CrudRepository<Country> {
    @Override
    List<Country> findAll();
}
