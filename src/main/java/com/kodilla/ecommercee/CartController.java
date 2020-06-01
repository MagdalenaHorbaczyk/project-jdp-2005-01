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
        System.out.println("Sztuczne dane - post");
    }

    @RequestMapping(method = RequestMethod.GET,value = "getCartProducts")
    public List<String> getCartProducts(@RequestParam Long cartId) {
    return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.PUT,value = "addProductToCart")
    public void addProductToCart(@RequestParam Long cartId, @RequestParam Long productId) {
        System.out.println("Sztuczne dane - put");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "removeProductFromCart")
    public void removeProductFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        System.out.println("Sztuczne dane - delete");
    }
}