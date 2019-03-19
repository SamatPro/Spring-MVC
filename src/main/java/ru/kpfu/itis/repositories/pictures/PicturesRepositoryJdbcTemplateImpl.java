package ru.kpfu.itis.repositories.pictures;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.models.City;
import ru.kpfu.itis.models.Picture;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
@Component("picturesRepositoryJdbcTemplateImpl")
public class PicturesRepositoryJdbcTemplateImpl implements PicturesRepository{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PicturesRepositoryJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final String FIND_ALL_PICTURES_BY_COUNTRY =
            "SELECT city.id AS city_id, pictures.id AS pic_id, href FROM country JOIN city ON country.id=city.country_id JOIN pictures ON city.id=pictures.city_id WHERE country.id=?;";

    private static final String FIND_ALL_PICTURES_BY_COUNTRY_NAME =
            "SELECT city.id AS city_id, pictures.id AS pic_id, href FROM country JOIN city ON country.id=city.country_id JOIN pictures ON city.id=pictures.city_id WHERE country.name=?;";



    @Override
    public List<Picture> findAllPicturesByCountryId(Long id) {
        return jdbcTemplate.query(FIND_ALL_PICTURES_BY_COUNTRY, picturesRowMapper, id);
    }

    @Override
    public List<Picture> findAllPicturesByCountryName(String name) {
        return jdbcTemplate.query(FIND_ALL_PICTURES_BY_COUNTRY_NAME, picturesRowMapper, name);
    }

    @Override
    public Optional<Picture> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Picture model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Picture model) {

    }

    @Override
    public List<Picture> findAll() {
        return null;
    }

    private RowMapper picturesRowMapper = (resultSet, i) ->Picture.builder()
            .id(resultSet.getLong("pic_id"))
            .city(
                    City.builder()
                            .id(resultSet.getLong("city_id"))
                            .build()
            )
            .href(resultSet.getString("href"))
            .build();
}
