package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DELIVERYADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryAddress {

    @Id
    @NotNull
    @GeneratedValue
    private Long deliveryAddressId;

    @NotNull
    private String name;

    @NotNull
    private String country;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String buildingNumber;

    private int flatNumber;

    @NotNull
    private String postcode;

    @NotNull
    private int phone;

    @OneToMany (
            mappedBy = "deliveryAddress",
            targetEntity = Order.class,
            fetch = FetchType.EAGER
    )
    private List<Order> orders = new ArrayList<>();

}
