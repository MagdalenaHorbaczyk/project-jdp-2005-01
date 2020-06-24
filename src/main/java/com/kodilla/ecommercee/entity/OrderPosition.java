package com.kodilla.ecommercee.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDERPOSITIONS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderPosition {

    @Id
    @NotNull
    @GeneratedValue
    private Long orderPositionId;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int quantity;

    @ManyToOne
    private Order order;

}
