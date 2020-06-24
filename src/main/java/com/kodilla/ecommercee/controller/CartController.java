package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.CartDto;
import com.kodilla.ecommercee.domain.ProductDto;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getCarts")
    public List<CartDto> getCarts() {
        return cartMapper.mapToCartDtoList((cartService.findAllCarts()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCart")
    public CartDto getCart(@RequestParam Long cartId) throws CartNotFoundException {
        return cartMapper.mapToCartDto(cartService.getCart(cartId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getCartProducts")
    public List<ProductDto> getCartProducts(@RequestParam Long cartId) throws CartNotFoundException {
        return productMapper.mapToProductDtoList(cartService.getCart(cartId).getProducts());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createCart", consumes = APPLICATION_JSON_VALUE)
    public void createCart(@RequestBody CartDto cartDto) {
        cartService.saveCart(cartMapper.mapToCart(cartDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCart", consumes = APPLICATION_JSON_VALUE)
    public CartDto updateCart(@RequestBody CartDto cartDto) {
        return cartMapper.mapToCartDto(cartService.saveCart(cartMapper.mapToCart(cartDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteCart")
    public void deleteCart(@RequestParam Long cartId) {
        cartService.deleteCart(cartId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "addProductToCart")
    public void addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) throws CartNotFoundException, ProductNotFoundException {
        cartService.addProductToCart(cartId, productId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "removeProductFromCart")
    public void removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) throws CartNotFoundException, ProductNotFoundException {
        cartService.removeProductFromCart(cartId, productId);
    }
}