package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDto {
    private Long cartId;
    private List<Product> products = new ArrayList<>();
}