package ru.kpfu.itis.repositories.cooperativeTours;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.mapper.RowMapper;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.CooperativeTours;
import ru.kpfu.itis.models.Order;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component("cooperativeToursRepositoryJdbcTemplateImpl")
public class CooperativeToursRepositoryImpl implements CooperativeToursRepository{

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_UPDATE_CANDIDATE = "INSERT INTO cooperative_tours(order_id, client_id) VALUES(?, ?);";
    private static final String SQL_FIND_UNC_TRS = "SELECT description, cooperative_tours.id AS coop_id, last_name, first_name,middle_name, name FROM client_db JOIN order_db ON client_db.id=order_db.client_id JOIN cooperative_tours ON order_db.id=order_id JOIN city ON order_db.city_id = city.id WHERE cooperative_tours.client_id=? AND is_consented ISNULL;";
    private static final String SQL_CONSENT = "UPDATE cooperative_tours SET is_consented=TRUE WHERE id = ?;";
    private static final String SQL_DELETE_ORDER = "DELETE FROM ONLY cooperative_tours WHERE id=?;";
    private static final String SQL_DELETE_ORDERS_BY_ORDER_ID = "DELETE FROM cooperative_tours WHERE order_id=?;";

    @Autowired
    public CooperativeToursRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addInvitation(Long orderId, Long clientId) {
        jdbcTemplate.update(SQL_UPDATE_CANDIDATE, orderId, clientId);
    }

    @Override
    public List<CooperativeTours> showUnConsentedTours(Long id) {
        return jdbcTemplate.query(SQL_FIND_UNC_TRS, cooperativeToursRowMapper, id);
    }


    private org.springframework.jdbc.core.RowMapper<CooperativeTours> cooperativeToursRowMapper = (resultSet, i) -> CooperativeTours.builder()
            .id(resultSet.getLong("coop_id"))
            .order(
                    Order.builder()
                            .city(
                                    City.builder()
                                            .name(resultSet.getString("name"))
                                            .description(resultSet.getString("description"))
                                            .build()
                            )
                            .client(
                                    Client.builder()
                                            .lastName(resultSet.getString("last_name"))
                                            .firstName(resultSet.getString("first_name"))
                                            .middleName(resultSet.getString("middle_name"))
                                            .build()
                            )
                            .build()
            )
            .build();

    @Override
    public void updateConsent(Long id) {
        jdbcTemplate.update(SQL_CONSENT, id);
    }

    @Override
    public void deleteConsent(Long id) {
        jdbcTemplate.update(SQL_DELETE_ORDER, id);
    }

    @Override
    public Optional<CooperativeTours> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(CooperativeTours model) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_ORDERS_BY_ORDER_ID, id);
    }

    @Override
    public void update(CooperativeTours model) {

    }

    @Override
    public List<CooperativeTours> findAll() {
        return null;
    }
}
