package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DELIVERYADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class DeliveryAddress {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

}
