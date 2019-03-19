package ru.kpfu.itis.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    private Long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String login;
    private String hashPassword;
    private String position;

}
