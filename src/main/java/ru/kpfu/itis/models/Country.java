package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Country {
    private Long id;
    private String name;
    private List<City> cities;

}
