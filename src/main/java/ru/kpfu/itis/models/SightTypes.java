package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SightTypes {
    private Long id;
    private String descripton;
    private List<Sight> sights;
}
