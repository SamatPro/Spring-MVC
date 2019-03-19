package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sight {
    private Long id;
    private String name;
    private String cityName;
    private List<SightTypes> types;
    private Long establishment;

}
