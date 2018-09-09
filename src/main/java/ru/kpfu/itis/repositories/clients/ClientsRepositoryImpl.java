package ru.kpfu.itis.repositories.clients;

import lombok.SneakyThrows;
import ru.kpfu.itis.models.Client;
import ru.kpfu.itis.mapper.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepositoryImpl implements ClientsRepository {

    private Connection connection;

    public ClientsRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAllByFirstName(String firstName) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM client_db WHERE gender = \'" + firstName +"\'");
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()){
                Client newClient = clientRowMapper.rowMap(resultSet);
                clients.add(newClient);
            }
            return clients;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Client> findAllByGender(String gender){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM client_db WHERE gender = \'" + gender +"\'");
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()){
                Client newClient = clientRowMapper.rowMap(resultSet);
                clients.add(newClient);
            }
            return clients;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Client> findAllByAge(Long age) {
        return null;
    }

    private RowMapper<Client> clientRowMapper = new RowMapper<Client>() {
        @Override
        @SneakyThrows
        public Client rowMap(ResultSet resultSet) {
            return Client.builder()
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .middleName(resultSet.getString("middle_name"))
                    .gender(resultSet.getString("gender"))
                    .phoneNumber(resultSet.getLong("phone_number"))
                    .address(resultSet.getString("address"))
                    .id(resultSet.getLong("client_id"))
                    .build();
        }
    };

    @Override
    public Client findOne(Long id) {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM client_db WHERE client_id=" + id);
            resultSet.next();
            return clientRowMapper.rowMap(resultSet);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Client model) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Client> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery("SELECT * FROM client_db");
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()){
                Client newClient = clientRowMapper.rowMap(resultSet);
                clients.add(newClient);
            }
            return clients;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
