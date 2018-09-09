package ru.kpfu.itis.repositories.cities;

import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Sight;
import ru.kpfu.itis.models.Transport;
import ru.kpfu.itis.repositories.CrudRepository;

import java.util.List;

public interface CitiesRepository extends CrudRepository<City> {
    List<City> findAllByCountry(String country);
    List<Transport> findCitiesByTransport(String transport);
    List<Sight> findCitiesSightsType(String type);
}
