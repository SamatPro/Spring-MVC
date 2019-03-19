package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalInfo {
    private Long id;
    private Long salary;
    private String address;
    private Long phoneNumber;
    private String gender;
    private Long itn;
}
