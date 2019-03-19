package ru.kpfu.itis.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeForm {
    String login;
    String password;
    String firstName;
    String lastName;
    String middleName;
    String position;
}
