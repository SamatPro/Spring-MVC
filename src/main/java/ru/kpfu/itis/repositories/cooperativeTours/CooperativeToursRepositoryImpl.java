package ru.kpfu.itis.repositories.cooperativeTours;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.CooperativeTour;
import ru.kpfu.itis.models.Order;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Component("cooperativeToursRepositoryJdbcTemplateImpl")
public class CooperativeToursRepositoryImpl implements CooperativeToursRepository{

    private JdbcTemplate jdbcTemplate;
    private static final String SQL_UPDATE_CANDIDATE = "INSERT INTO cooperative_tour(order_id, client_id) VALUES(?, ?);";
    private static final String SQL_FIND_UNC_TRS = "SELECT description, cooperative_tour.id AS coop_id, last_name, first_name,middle_name, city.name FROM client JOIN orders ON client.id=orders.client_id JOIN cooperative_tour ON orders.id=order_id JOIN city ON orders.city_id = city.id WHERE cooperative_tour.client_id=? AND is_consented ISNULL;";
    private static final String SQL_CONSENT = "UPDATE cooperative_tour SET is_consented=TRUE WHERE id = ?;";
    private static final String SQL_DELETE_ORDER = "DELETE FROM ONLY cooperative_tour WHERE id=?;";
    private static final String SQL_DELETE_ORDERS_BY_ORDER_ID = "DELETE FROM cooperative_tour WHERE order_id=?;";

    @Autowired
    public CooperativeToursRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addInvitation(Long orderId, Long clientId) {
        jdbcTemplate.update(SQL_UPDATE_CANDIDATE, orderId, clientId);
    }

    @Override
    public List<CooperativeTour> showUnConsentedTours(Long id) {
        return jdbcTemplate.query(SQL_FIND_UNC_TRS, cooperativeToursRowMapper, id);
    }


    private org.springframework.jdbc.core.RowMapper<CooperativeTour> cooperativeToursRowMapper = (resultSet, i) -> CooperativeTour.builder()
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
    public Optional<CooperativeTour> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(CooperativeTour model) {

    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_ORDERS_BY_ORDER_ID, id);
    }

    @Override
    public void update(CooperativeTour model) {

    }

    @Override
    public List<CooperativeTour> findAll() {
        return null;
    }
}
