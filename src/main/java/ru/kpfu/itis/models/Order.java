package ru.kpfu.itis.models;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Date issueDate;
    private List<Tour> tours;
    private List<Employee> employees;
    private List<Client> clients;

}
