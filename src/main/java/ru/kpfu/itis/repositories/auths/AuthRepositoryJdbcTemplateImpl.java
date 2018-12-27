package ru.kpfu.itis.repositories;

import lombok.SneakyThrows;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.kpfu.itis.models.Auth;
import ru.kpfu.itis.models.Client;

import javax.servlet.http.Cookie;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {

    private JdbcTemplate jdbcTemplate;

//    private Map<Long, Client> clientWithCookieMap;

    private static final String SQL_INSERT_QUERY =
            "INSERT INTO auth(client_id, cookie_value) values (?, ?);";
    private static final String SQL_INSERT_EMPLOYEE_QUERY =
            "INSERT INTO auth(employee_id, cookie_value) values (?, ?);";
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "SELECT * FROM auth WHERE cookie_value = ?;";
    private static final String SQL_SELECT_CLIENT_BY_COOKIE =
            "SELECT * FROM client_db JOIN auth ON client_db.id=auth.client_id WHERE auth.cookie_value=?;";
    private static final String SQL_DELETE_COOKIE =
            "DELETE FROM ONLY auth WHERE cookie_value=?;";

    public AuthRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Auth> findByCookieValue(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public void update(Auth model) {

    }

    @Override
    public Optional<Auth> findOne(Long id) {
        return  null;
    }

    @Override
    public void save(Auth model) {
        jdbcTemplate.update(SQL_INSERT_QUERY, model.getClient().getId(), model.getCookieValue());
    }

    @Override
    public void saveEmployee(Auth employee) {
        jdbcTemplate.update(SQL_INSERT_EMPLOYEE_QUERY, employee.getEmployee().getId(), employee.getCookieValue());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    private RowMapper<Auth> authRowMapper = (resultSet, i) ->  Auth.builder()
//            .client(resultSet.getLong("client_id"))
            .cookieValue(resultSet.getString("cookie_value"))
            .build();

    /*@Override
    public Optional<Client> findClientByCookie(String cookieValue) {
        try{
            Optional.of(jdbcTemplate.update(SQL_SELECT_CLIENT_BY_COOKIE, clientByCookieRowMapper, cookieValue));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }*/

//    private ru.kpfu.itis.mapper.RowMapper<Auth> clientWithCookieRowMapper = new ru.kpfu.itis.mapper.RowMapper<Auth>() {
//        @Override
//        @SneakyThrows
//        public Auth rowMap(ResultSet resultSet) {
//            Auth newAuth = authRowMapper.mapRow(resultSet, 2);
//            new
//        }
//    };
    /*private RowMapper<Client> clientByCookieRowMapper = (resultSet, i) -> Client.builder()
        .lastName(resultSet.getString("last_name"))
        .firstName(resultSet.getString("first_name"))
       */

    @Override
    public Optional<Client> findClientByCookie(String cookieValue) {
        return null;
    }

    @Override
    public void deleteCookie(String cookieValue) {
        jdbcTemplate.update(SQL_DELETE_COOKIE, cookieValue);
    }

    @Override
    public Optional<Auth> findByCookieValueEmployee(String cookieValue) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_BY_COOKIE_VALUE, authRowMapper, cookieValue));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }
}
