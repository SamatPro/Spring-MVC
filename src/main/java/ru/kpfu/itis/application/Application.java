package ru.kpfu.itis.application;
import java.sql.*;

public class Application {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT city_id, name FROM city");
            while (resultSet.next()){
                System.out.println(resultSet.getLong("city_id") + " " + resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }
}
