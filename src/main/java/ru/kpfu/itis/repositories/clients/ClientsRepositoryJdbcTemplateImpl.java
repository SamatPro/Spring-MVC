package ru.kpfu.itis.repositories.clients;

import com.sun.org.apache.regexp.internal.RE;
import lombok.SneakyThrows;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.models.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;
@Component("clientsRepositoryJdbcTemplateImpl")
public class ClientsRepositoryJdbcTemplateImpl implements ClientsRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_BY_FIRST_NAME = "SELECT id, first_name, last_name, middle_name FROM client WHERE first_name ILIKE ? || '%' OR last_name ILIKE ? || '%' OR middle_name ILIKE ? || '%';";
    private static final String SQL_FIND_ONLY_WITH_NAME = "SELECT id, first_name, last_name FROM client;";
    private static final String SQL_INSERT_QUERY = "INSERT INTO client (last_name, first_name, middle_name, address, phone_number, ismale, email, hash_password, news_subscription) "+
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final  String DELETE_BY_ID = "DELETE FROM ONLY client WHERE id=?;";
    private static final String SQL_FIND_ONE_BY_ID_QUERY = "SELECT * FROM client WHERE id = ?;";
    private static final String SELECT_CLIENT_BY_EMAIL_QUERY = "SELECT * FROM client WHERE email=?;";
    private static final String SQL_SELECT_CLIENT_BY_COOKIE =
            "SELECT * FROM client LEFT JOIN auth ON client.id=auth.client_id WHERE auth.cookie_value=?;";

    @Autowired
    public ClientsRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Client> findAllByFirstName(String firstName) {
        return jdbcTemplate.query(SQL_FIND_BY_FIRST_NAME, clientOnlyWithNameRowMapper, firstName, firstName, firstName);
    }

    @Override
    public Optional<Client> findOne(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_ID_QUERY, clientRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
    @Override
    public Optional<Client> findClientByCookie(String cookieValue) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_CLIENT_BY_COOKIE, clientRowMapper, cookieValue));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void save(Client model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getLastName());
                    ps.setString(2, model.getFirstName());
                    ps.setString(3, model.getMiddleName());
                    ps.setString(4, model.getAddress());
                    ps.setLong(5, model.getPhoneNumber());
                    ps.setBoolean(6, model.getIsMale());
                    ps.setString(7, model.getEmail());
                    ps.setString(8, model.getHashPassword());
                    ps.setBoolean(9, model.getNewsSubscription());
                    return ps;
                }, keyHolder
        );
        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public void update(Client model) {

    }

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Optional<Client> findOneByEmail(String email) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SELECT_CLIENT_BY_EMAIL_QUERY, clientRowMapper, email));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
    private RowMapper<Client> clientRowMapper = (resultSet, i) -> Client.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .middleName(resultSet.getString("middle_name"))
            .email(resultSet.getString("email"))
            .hashPassword(resultSet.getString("hash_password"))
            .isMale(resultSet.getBoolean("ismale"))
            .birthDate(resultSet.getDate("birth_date"))
            .address(resultSet.getString("address"))
            .newsSubscription(resultSet.getBoolean("news_subscription"))
            .build();

    @Override
    public List<Client> findAllOnlyWithName() {
        return jdbcTemplate.query(SQL_FIND_ONLY_WITH_NAME, clientRowMapperWithNames);
    }

    private RowMapper<Client> clientRowMapperWithNames = (resultSet, i) -> Client.builder()
            .id(resultSet.getLong("id"))
            .firstName(resultSet.getString("first_name"))
            .lastName(resultSet.getString("last_name"))
            .build();

     private RowMapper<Client> clientOnlyWithNameRowMapper = (resultSet, i) -> Client.builder()
            .id(resultSet.getLong("id"))
            .lastName(resultSet.getString("last_name"))
            .firstName(resultSet.getString("first_name"))
            .middleName(resultSet.getString("middle_name"))
            .build();

}
