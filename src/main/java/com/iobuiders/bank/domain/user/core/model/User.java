package com.iobuiders.bank.domain.user.core.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user", schema = "bank")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    private EmailAddress emailAddress;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="user_name")
    private String username;

    @Column(name="password")
    private String password;

    public User(EmailAddress emailAddress, String firstName, String lastName, String username, String password) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }
}
