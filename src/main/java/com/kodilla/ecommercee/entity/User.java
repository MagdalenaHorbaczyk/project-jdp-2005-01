package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
