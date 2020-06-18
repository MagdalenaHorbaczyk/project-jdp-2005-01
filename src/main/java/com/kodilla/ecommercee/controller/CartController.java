package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")

public class CartController {

    @RequestMapping(method = RequestMethod.GET,value = "getCartProducts")
    public List<ProductDto> getCartProducts() {
        List<ProductDto> Cart = new ArrayList<>();
        Cart.add(new ProductDto(1L, "aaa", "bbb", new BigDecimal("222"), null, null));
        Cart.add(new ProductDto(2L, "aaaaaa", "bbbdddd", new BigDecimal("2222"), null, null));
        Cart.add(new ProductDto(3L, "aaaaaaaaaaa", "bbbdddddddd", new BigDecimal("22222"), null, null));
        return Cart;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @RequestMapping(method = RequestMethod.PUT,value = "addProductToCart")
    public CartDto addProduct(@RequestBody CartDto cartDto) {
        List<ProductDto> CartPUT = new ArrayList<>();
        CartPUT.add(new ProductDto(1L, "aaaaaaaaaaa", "bbbdddddddd", new BigDecimal("22222"), null, null));
        CartPUT.add(new ProductDto(2L, "aaaaaaaaaaa", "bbbdddddddd", new BigDecimal("22222"), null, null));
        CartPUT.add(new ProductDto(3L, "aaaaaaaaaaa", "bbbdddddddd", new BigDecimal("22222"), null, null));
        return new CartDto(10L,CartPUT, 1021L);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "removeProductFromCart")
    public void removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {

    }
}
