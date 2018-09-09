package ru.kpfu.itis.reposirories.cities;

import ru.kpfu.itis.mappers.City;
import ru.kpfu.itis.reposirories.CrudRepository;

import java.util.List;

public interface CitiesRepository extends CrudRepository {
    List<City> findAllByCountry(String country);

}
