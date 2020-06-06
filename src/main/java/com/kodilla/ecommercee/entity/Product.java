package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCTS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @NotNull
    @GeneratedValue
    private Long productId;

    @NotNull
    private String name;

}
