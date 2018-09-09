package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Sight {
    private Long id;
    private String name;
    private String cityName;
    private String type;
    private Long establishment;
}
