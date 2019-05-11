package ru.kpfu.itis.repositories.cities;

import ru.kpfu.itis.models.City;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;

public interface CitiesRepository extends CrudRepository<City> {
    List<City> findCitiesByCountryId(Long id);
    List<City> findCitiesByCountryName(String name);

}
