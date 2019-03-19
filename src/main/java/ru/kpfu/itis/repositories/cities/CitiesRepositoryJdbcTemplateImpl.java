package ru.kpfu.itis.repositories.cities;

import com.sun.org.apache.regexp.internal.RE;
import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Country;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
import java.lang.*;
@Component("citiesRepositoryJdbcTemplateImpl")
public class CitiesRepositoryJdbcTemplateImpl implements CitiesRepository{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_UPDATE = "UPDATE city SET name=?, country_id=? WHERE id=?;";
    private static final String SQL_INSERT_QUERY = "INSERT INTO city(name, country_id) "+
            "VALUES (?, ?);";

    private static final String DELETE_BY_ID = "DELETE FROM ONLY city WHERE id=?;";
    private static final String FIND_ONE = "SELECT * FROM city WHERE id=?;";
    private static final String FIND_ALL_CITIES = "SELECT id, name FROM city;";
    private static final String FIND_CITIES_BY_COUNTRY_ID =
            "SELECT *,city.id AS city_id, country.name AS country_name, city.name AS city_name FROM country JOIN city ON country.id=city.country_id WHERE country.id=?;";

    private static final String FIND_CITIES_BY_COUNTRY_NAME =
            "SELECT *,city.id AS city_id, country.name AS country_name, city.name AS city_name FROM country JOIN city ON country.id=city.country_id WHERE country.name=?;";

    @Autowired
    public CitiesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<City> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(FIND_ONE, oneCityRowMapper, id));
    }
    private RowMapper<City> oneCityRowMapper = (resultSet, i) -> City.builder()
            .name(resultSet.getString("name"))
            .description(resultSet.getString("description"))
            .build();

    @Override
    public void save(City model) {
        jdbcTemplate.update(SQL_INSERT_QUERY, model.getName(), model.getCountry().getId());
    }

    @Override
    @SneakyThrows
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public List<City> findAll() {
        return jdbcTemplate.query(FIND_ALL_CITIES, rowMapper);
    }

    @Override
    public void update(City model) {
        jdbcTemplate.update(SQL_UPDATE, model.getName(), model.getCountry().getId(), model.getId());
    }

    @Override
    public List<City> findCitiesByCountryId(Long id) {
        return jdbcTemplate.query(FIND_CITIES_BY_COUNTRY_ID, cityRowMapperWithCountry, id);
    }

    @Override
    public List<City> findCitiesByCountryName(String name) {
        return jdbcTemplate.query(FIND_CITIES_BY_COUNTRY_NAME, cityRowMapperWithCountry, name);
    }

    private RowMapper rowMapper = (resultSet, i) -> City.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .build();

    private RowMapper cityRowMapperWithCountry = (resultSet, i) -> City.builder()
            .id(resultSet.getLong("city_id"))
            .description(resultSet.getString("description"))
            .country(
                    Country.builder()
                            .name(resultSet.getString("country_name"))
                            .build()
            )
            .name(resultSet.getString("city_name"))
            .build();

}
