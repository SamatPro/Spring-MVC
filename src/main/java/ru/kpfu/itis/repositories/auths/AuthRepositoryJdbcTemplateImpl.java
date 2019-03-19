package ru.kpfu.itis.repositories.auths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Auth;
import ru.kpfu.itis.models.Client;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Component("authRepositoryJdbcTemplateImpl")
public class AuthRepositoryJdbcTemplateImpl implements AuthRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_QUERY =
            "INSERT INTO auth(client_id, cookie_value) values (?, ?);";
    private static final String SQL_INSERT_EMPLOYEE_QUERY =
            "INSERT INTO auth(employee_id, cookie_value) values (?, ?);";
    private static final String SQL_SELECT_BY_COOKIE_VALUE =
            "SELECT * FROM auth WHERE cookie_value = ?;";
    private static final String SQL_DELETE_COOKIE =
            "DELETE FROM ONLY auth WHERE cookie_value=?;";
    private static final String SQL_UPDATE =
            "UPDATE auth SET client_id=?, cookie_value=? WHERE client_id=?;";
    private static final String FIND_ONE =
            "SELECT client_id, cookie_value FROM auth WHERE id=?;";
    private static final String SQL_DELETE =
            "DELETE FROM ONLY auth WHERE id = ?";

    @Autowired
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
        jdbcTemplate.update(SQL_UPDATE, model.getClient().getId(), model.getCookieValue(), model.getClient().getId());
    }

    @Override
    public Optional<Auth> findOne(Long id) {
        return  Optional.of(jdbcTemplate.queryForObject(FIND_ONE,cookieWithClientIDRowMapper, id));
    }

    private RowMapper<Auth> cookieWithClientIDRowMapper = (resultSet, i) -> Auth.builder()
            .client(
                    Client.builder()
                    .id(resultSet.getLong("client_id"))
                    .build()
            )
            .cookieValue(resultSet.getString("cookie_value"))
            .build();

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
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public List<Auth> findAll() {
        return null;
    }

    private RowMapper<Auth> authRowMapper = (resultSet, i) ->  Auth.builder()
            .cookieValue(resultSet.getString("cookie_value"))
            .build();



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
