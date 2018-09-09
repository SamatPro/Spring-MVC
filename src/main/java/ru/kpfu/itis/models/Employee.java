package ru.kpfu.itis.models;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Employee {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private Date birthDate;
    private Integer salary;
    private String position;
    private String address;
    private Long phoneNumber;

}
