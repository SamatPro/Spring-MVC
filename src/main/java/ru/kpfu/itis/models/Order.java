package ru.kpfu.itis.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Order {
    private Long id;
    private Date issueDate;
    private List<Tour> tours;
    private List<Employee> employees;
    private List<Client> clients;

}
