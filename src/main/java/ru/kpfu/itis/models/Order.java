package ru.kpfu.itis.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    private Long id;
    private Date issueDate;
    private Long price;
    private List<Tour> tours;
    private Employee employee;
    private Client client;
    private Boolean isAccepted;
    private City city;
}
