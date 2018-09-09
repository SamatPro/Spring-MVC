package ru.kpfu.itis.mappers;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String address;
    private Long phoneNumber;

}

