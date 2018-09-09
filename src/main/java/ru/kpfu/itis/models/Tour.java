package ru.kpfu.itis.models;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Tour {
    private Long id;
    private Date departureDate;
    private Date arrivalDate;
    private Integer peopleAmount;
    private List<Services> services;
    private List<Transport> transports;
}
