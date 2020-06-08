package com.kodilla.ecommercee;



import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController

@RequestMapping("/v1/cart")

public class CartController {


    @RequestMapping(method = RequestMethod.POST,value = "createCart")
    public void createCart(@RequestBody CartDto cartDto) {
    }

    @RequestMapping(method = RequestMethod.GET,value = "getCartProducts")
    public List<CartDto> getCartProducts() {
        List<String> ListProd = new ArrayList<>();
        ListProd.add("Krzeslo");
        ListProd.add("Stół");
        CartDto C1 = new CartDto(10L,ListProd, 20L);
    return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT,value = "addProductToCart")
    public CartDto addProductToCart(@RequestParam Long id) {
        List<String> ListProd = new ArrayList<>();
        ListProd.add("Krzeslo");
        ListProd.add("Stół");
        return new CartDto(10L,ListProd, 20L) ;
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "removeProductFromCart")
    public void removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {

    }
}