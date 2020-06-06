package com.kodilla.ecommercee.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table
public class User {

    @Id
    @NotNull
    @GeneratedValue
    private Long id;

    private String login;

    private String userKey;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
