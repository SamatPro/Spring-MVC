package ru.kpfu.itis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class Services {
    private Long id;
    private String accomodations;
    private String food;
    private String excurtion;
    private List<Country> countries;

}
