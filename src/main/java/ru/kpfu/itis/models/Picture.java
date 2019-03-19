package ru.kpfu.itis.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Picture {
    private Long id;
    private String href;
    private City city;
}
