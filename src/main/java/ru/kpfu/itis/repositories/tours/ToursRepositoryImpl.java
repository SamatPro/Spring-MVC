package ru.kpfu.itis.repositories.tours;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.models.Tour;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ToursRepositoryImpl implements ToursRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String DELETE_BY_ID = "DELETE FROM ONLY tour_db WHERE id=?;";
    private static final String SQL_INSERT_QUERY = "INSERT INTO tour_db(departure_date, arrival_date) VALUES (?, ?, ?);";
    private static final String SQL_FIND_ONE_BY_ID_QUERY = "SELECT * FROM tour_db WHERE id = ?";

    @Autowired
    public ToursRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Tour model) {
        jdbcTemplate.update(SQL_INSERT_QUERY, model.getDepartureDate(), model.getArrivalDate());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Tour model) {

    }

    @Override
    public List<Tour> findAll() {
        return null;
    }

    private RowMapper tourRowMapper = (resultSet, i) -> Tour.builder()
            .id(resultSet.getLong("id"))
            .build();

    @Override
    public Optional<Tour> findOne(Long id) {
        try{
            return Optional.of((Tour) jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_ID_QUERY, tourRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
