package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column("USER_NAME")
    private String login;

    private String userKey;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

}
