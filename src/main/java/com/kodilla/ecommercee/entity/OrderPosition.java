package com.kodilla.ecommercee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "ORDERPOSITIONS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderPosition {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ORDER_POSITION_ID")
    private Long orderPositionId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull
    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;


    @Override
    public String toString() {
        return "OrderPosition details: " + "\n" +
                "orderPositionId = " + orderPositionId + "\n" +
                "ordered item name = " + name + "\n" +
                "price = " + price + "\n" +
                "quantity = " + quantity;
    }
}
