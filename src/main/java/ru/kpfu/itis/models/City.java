package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    private Long id;
    private String name;
    private Country country;
    private String description;
    private List<Sight> sights;
    private List<Picture> pictures;
}
