package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class City {
    private Long id;
    private String name;
    private String country;
    private List<Sight> sights;

}
