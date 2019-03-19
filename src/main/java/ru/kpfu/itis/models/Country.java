package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    private Long id;
    private String name;
    private List<City> cities;

}
