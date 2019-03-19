package ru.kpfu.itis.mapper;

import java.sql.ResultSet;

public interface RowMapper<T> {
    T rowMap(ResultSet resultSet);
}
