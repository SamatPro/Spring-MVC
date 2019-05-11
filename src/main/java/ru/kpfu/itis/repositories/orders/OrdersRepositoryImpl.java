package ru.kpfu.itis.repositories.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.*;
@Component("ordersRepositoryJdbcTemplateImpl")
public class OrdersRepositoryImpl implements OrdersRepository {

    private JdbcTemplate jdbcTemplate;

    private static final String DELETE_BY_ID = "DELETE FROM ONLY orders WHERE id=?;";
    private static final String ADD_ORDER = "INSERT INTO orders(client_id, city_id) VALUES (?, ?);";
    private static final String FIND_RAW_ORDERS_WITH_CLIENT = "SELECT *, orders.id AS order_id" +
            " FROM client JOIN"+
            " orders ON client.id = orders.client_id JOIN city ON orders.city_id = city.id  WHERE orders.employee_id ISNULL ORDER BY order_id;";
    private static final String SQL_MAKE_CHANGES = "UPDATE orders SET employee_id=?, isaccepted=? WHERE id=?";
    private static final String FIND_ORDERS_ONE_CLIENT =
            "SELECT *, orders.id AS order_id, " +
                    "employee.first_name AS e_name, " +
                    "employee.last_name AS e_lname " +
                    " FROM client JOIN"+
                    " orders ON client.id " +
                    "= orders.client_id JOIN city ON " +
                    "orders.city_id = city.id JOIN " +
                    "employee ON employee.id=orders.employee_id " +
                    " WHERE client_id=? ORDER BY order_id;";
    @Autowired
    public OrdersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<Order> findOne(Long id) {
        return null;
    }

    @Override
    public Long addOrderForCoop(Long clientId, Long cityId) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(ADD_ORDER, new String[]{"id"});
                    ps.setLong(1, clientId);
                    ps.setLong(2, cityId);
                    return ps;
                }, keyHolder
        );
        return Long.parseLong(keyHolder.getKey().toString());
    }

    @Override
    public void save(Order model) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_BY_ID, id);
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(FIND_RAW_ORDERS_WITH_CLIENT, orderRowMapper);
    }

    @Override
    public void update(Order model) {

    }

    @Override
    public void addOrder(Long clientId, Long cityId) {
        jdbcTemplate.update(ADD_ORDER, clientId, cityId);
    }


    private RowMapper<Order> orderRowMapper = (resultSet, i) -> Order.builder()
            .id(resultSet.getLong("order_id"))
            .client(
                    Client.builder()
                            .id(resultSet.getLong("client_id"))
                            .firstName(resultSet.getString("first_name"))
                            .lastName(resultSet.getString("last_name"))
                            .middleName(resultSet.getString("middle_name"))
                            .address(resultSet.getString("address"))
                            .email(resultSet.getString("email"))
                            .phoneNumber(resultSet.getLong("phone_number"))
                            .build()
            )
            .city(
                    City.builder()
                            .name(resultSet.getString("name"))
                            .build()
            )
            .price(resultSet.getLong("price"))
            .build();

    @Override
    public void makeChanges(Long employeeId, Boolean isAccepted, Long orderId) {
        jdbcTemplate.update(SQL_MAKE_CHANGES, employeeId, isAccepted, orderId);
    }

    @Override
    public List<Order> findOrdersOfClientById(Long id) {
        return jdbcTemplate.query(FIND_ORDERS_ONE_CLIENT, orderForOneClientRowMapper, id);
    }
    private RowMapper<Order> orderForOneClientRowMapper = (resultSet, i) -> Order.builder()
            .id(resultSet.getLong("order_id"))
            .city(
                    City.builder()
                            .name(resultSet.getString("name"))
                            .build()
            )
            .isAccepted(resultSet.getBoolean("isaccepted"))
            .employee(
                    Employee.builder()
                            .firstName(resultSet.getString("e_name"))
                            .lastName(resultSet.getString("e_lname"))
                            .position(resultSet.getString("position"))
                            .build()
            )
            .build();
}
