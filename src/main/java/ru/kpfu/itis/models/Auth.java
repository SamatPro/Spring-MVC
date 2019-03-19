package ru.kpfu.itis.models;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Auth {
    private Long id;
    private String cookieValue;
    private Client client;
    private Employee employee;
}
