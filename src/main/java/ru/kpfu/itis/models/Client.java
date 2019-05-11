package ru.kpfu.itis.models;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Client")
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "middle_name")
    private String middleName;
    private String email;
    @Column(name = "hash_password")
    private String hashPassword;
    @Column(name = "ismale")
    private Boolean isMale;
    private String address;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "news_subscription")
    private Boolean newsSubscription;
    @Column(name = "phone_number")
    private Long phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<Review> reviews;
    @OneToMany(mappedBy = "client")
    private List<Order> orderList;

    @OneToOne(mappedBy = "client")
    private Auth auth;

}

