package ru.kpfu.itis.application;

import lombok.SneakyThrows;
import ru.kpfu.itis.repositories.clients.ClientsRepository;
import ru.kpfu.itis.repositories.clients.ClientsRepositoryImpl;

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
        //Client client = clientsRepository.findOne(2L);
        //System.out.println(client);
        //System.out.println(clientsRepository.findAll());
        //Client client1 = clientsRepository.findAllByFirstName("Samat");
        System.out.println(clientsRepository.findAllByGender("m"));
        System.out.println(clientsRepository.findAllByFirstName("Emil"));
    }

}
