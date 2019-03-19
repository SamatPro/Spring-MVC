package ru.kpfu.itis.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CooperativeTours {
    private Long id;
    private Boolean isConsented;
    private Order order;
    private Client client;

}
