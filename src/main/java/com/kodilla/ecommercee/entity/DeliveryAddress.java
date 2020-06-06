package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DELIVERYADDRESS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeliveryAddress {

    @Id
    @NotNull
    @GeneratedValue
    private Long addressId;

    @NotNull
    private String address;

}
