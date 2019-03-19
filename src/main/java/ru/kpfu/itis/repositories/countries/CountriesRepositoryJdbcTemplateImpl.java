package ru.kpfu.itis.repositories.countries;

import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Country;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Component("countriesRepositoryJdbcTemplateImpl")
public class CountriesRepositoryJdbcTemplateImpl implements CountriesRepository{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_QUERY = "INSERT INTO country(name) "+
            "VALUES (?);";
    private static final String DELETE_BY_ID = "DELETE FROM ONLY city WHERE id=?;";
    private static final String SELECT_COUNTRY_BY_ID = "SELECT name FROM country WHERE id=?;";
    private static final String SELECT_ALL_COUNTRIES = "SELECT * FROM country;";

    @Autowired
    public CountriesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Country> findOne(Long id) {
        return Optional.of(jdbcTemplate.queryForObject(SELECT_COUNTRY_BY_ID, countryRowMapper, id));
    }

    @Override
    public void save(Country model) {
        jdbcTemplate.update(SQL_INSERT_QUERY, model.getName());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID,id);
    }

    @Override
    public List<Country> findAll() {
        return jdbcTemplate.query(SELECT_ALL_COUNTRIES, countryRowMapper);
    }

    @Override
    public void update(Country model) {

    }
    private RowMapper<Country> countryRowMapper = (resultSet, i) -> Country.builder()
            .name(resultSet.getString("name"))
            .build();
}
