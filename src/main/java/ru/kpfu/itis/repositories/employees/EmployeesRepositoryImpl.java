package ru.kpfu.itis.repositories.employees;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Employee;
import ru.kpfu.itis.models.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component("employeesRepositoryJdbcTemplateImpl")
public class EmployeesRepositoryImpl implements EmployeesRepository{

    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_EMPLOYEE_BY_COOKIE =
            "SELECT * FROM employee_db LEFT JOIN auth ON employee_db.id=auth.employee_id WHERE auth.cookie_value=?;";

    private static final String SQL_INSERT_QUERY = "INSERT INTO employee_db(login, hash_password, last_name, first_name, middle_name, position) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String DELETE_BY_ID = "DELETE FROM ONLY employee_db WHERE id=?;";
    private static final String SELECT_EMPLOYEE_BY_LOGIN = "SELECT * FROM employee_db WHERE login = ?;";
    private static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM employee_db WHERE id = ?;";


    @Autowired
    public EmployeesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Employee> findOneByLogin(String login) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_LOGIN, employeeRowMapper, login));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public Optional<Employee> findEmployeeByCookie(String cookieValue) {
        try{
            return Optional.of(jdbcTemplate.queryForObject(SQL_SELECT_EMPLOYEE_BY_COOKIE, employeeRowMapper, cookieValue));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    @Override
    public Optional<Employee> findOne(Long id) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SELECT_EMPLOYEE_BY_ID, employeeRowMapper, id));
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    @SneakyThrows
    public void save(Employee model) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(SQL_INSERT_QUERY, new String[]{"id"});
                    ps.setString(1, model.getLogin());
                    ps.setString(2, model.getHashPassword());
                    ps.setString(3, model.getLastName());
                    ps.setString(4, model.getFirstName());
                    ps.setString(5, model.getMiddleName());
                    ps.setString(6, model.getPosition());
                    return ps;
                }, keyHolder
        );
        model.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void update(Employee model) {
    }
    private RowMapper<Employee> employeeRowMapper = (resultSet, i) -> Employee.builder()
            .id(resultSet.getLong("id"))
            .lastName(resultSet.getString("last_name"))
            .firstName(resultSet.getString("first_name"))
            .middleName(resultSet.getString("middle_name"))
            .login(resultSet.getString("login"))
            .hashPassword(resultSet.getString("hash_password"))
            .position(resultSet.getString("position"))
            .build();
}
