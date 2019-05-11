package ru.kpfu.itis.repositories.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.models.Review;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component("reviewsRepositoryJdbcTemplateImpl")
public class ReviewsRepositoryJdbcTemplateImpl implements ReviewsRepository {
  private static final String ADD_REVIEW = "INSERT INTO reviews(client_id, text) VALUES (?, ?);";
  private static final String FIND_ALL = "SELECT * FROM reviews JOIN client ON client.id=reviews.client_id;";

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public ReviewsRepositoryJdbcTemplateImpl(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Optional<Review> findOne(Long id) {
    return Optional.empty();
  }

  @Override
  public void save(Review model) {

  }

  @Override
  public void delete(Long id) {

  }

  @Override
  public void update(Review model) {

  }

  @Override
  public List<Review> findAll() {
    return jdbcTemplate.query(FIND_ALL, reviewsRowMapper);
  }

  private RowMapper reviewsRowMapper = (resultSet, i) -> Review.builder()
          .client(
                  Client.builder()
                          .firstName(resultSet.getString("first_name"))
                          .lastName(resultSet.getString("last_name"))
                          .build()
          )
          .text(resultSet.getString("text"))
          .build();

  @Override
  public void addReview(Long clientId, String text) {
    jdbcTemplate.update(ADD_REVIEW, clientId, text);
  }
}
