package ru.kpfu.itis.models;

import lombok.*;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class Client {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String gender;
    private String address;
    private Long phoneNumber;

    @Override
    public String toString() {
        return "\nClient{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                "}";
    }
}

