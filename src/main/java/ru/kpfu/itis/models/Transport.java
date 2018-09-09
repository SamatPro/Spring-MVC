package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Transport {
    private Long id;
    private String name;
}
