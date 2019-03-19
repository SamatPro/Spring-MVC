package ru.kpfu.itis.models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String hashPassword;
    private Boolean isMale;
    private String address;
    private Date birthDate;
    private Boolean newsSubscription;
    private Long phoneNumber;
    private String review;
    private List<Order> orderList;

}

