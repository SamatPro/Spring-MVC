package ru.kpfu.itis.application;

import lombok.SneakyThrows;
import ru.kpfu.itis.mappers.Client;
import ru.kpfu.itis.reposirories.clients.ClientsRepository;
import ru.kpfu.itis.reposirories.clients.ClientsRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClientRepositoryDemo {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    @SneakyThrows
    public static void main(String[] args) {
        Connection connection =
                DriverManager.getConnection(URL, USERNAME, PASSWORD);
        ClientsRepository clientsRepository = new ClientsRepositoryImpl(connection);
        Client client = clientsRepository.findOne(2L);
        System.out.println(client);
        System.out.println(clientsRepository.findAll());
    }

}
