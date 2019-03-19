package ru.kpfu.itis.models;

import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {
    private Long id;
    private LocalDate departureDate;
    private LocalDate arrivalDate;

}
