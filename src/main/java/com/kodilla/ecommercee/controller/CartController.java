package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")

public class CartController {

    @RequestMapping(method = RequestMethod.GET,value = "getCartProducts")
    public List<ProductDto> getCartProducts() {
        List<ProductDto> Cart = new ArrayList<>();
        Cart.add(new ProductDto(1L, "Masło", "Masełko", 4.20, 100L));
        Cart.add(new ProductDto(2L, "Mleko", "Mleko", 2.30, 200L));
        Cart.add(new ProductDto(3L, "Ser ", "Ser", 12.50, 300L));
        return Cart;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @RequestMapping(method = RequestMethod.PUT,value = "addProductToCart")
    public CartDto addProduct(@RequestBody CartDto cartDto) {
        List<ProductDto> CartPUT = new ArrayList<>();
        CartPUT.add(new ProductDto(1L, "Masełko", "Masełko", 4.20, 100L));
        CartPUT.add(new ProductDto(2L, "Mleko", "Mleko", 2.30, 200L));
        CartPUT.add(new ProductDto(3L, "Ser", "Ser", 12.50, 300L));
        return new CartDto(10L,CartPUT, 1021L);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "removeProductFromCart")
    public void removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {

    }
}