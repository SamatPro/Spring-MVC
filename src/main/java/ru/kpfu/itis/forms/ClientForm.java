package ru.kpfu.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientForm {
    String email;
    String password;
    String repassword;
    String firstName;
    String lastName;
    String middleName;
    String address;
    Long phoneNumber;
    Boolean isMale;
    Boolean newsSubscription;
    Boolean consent;
}
