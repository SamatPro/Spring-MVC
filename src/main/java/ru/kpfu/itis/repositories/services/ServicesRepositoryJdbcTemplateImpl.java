package ru.kpfu.itis.repositories.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.models.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ServicesRepositoryJdbcTemplateImpl implements ServicesRepository{

    private static final String SQL_FIND_ONE_BY_ID_QUERY = "SELECT * FROM service WHERE id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM ONLY service WHERE id = ?;";

    private static final String SQL_INSERT_QUERY = "INSERT INTO service(id, accomodation, food, excurtion) VALUES (?, ?, ?, ?);";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ServicesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Service> findOne(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_ID_QUERY, servicesRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Service model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Service model) {

    }

    private RowMapper<Service> servicesRowMapper = (resultSet, i) -> Service.builder()
            .id(resultSet.getLong("id"))
            .excurtion(resultSet.getString("excurtion"))
            .food(resultSet.getString("food"))
            .accomodations(resultSet.getString("accomodations"))
            .build();


    @Override
    public List<Service> findAll() {
        return null;
    }
}
