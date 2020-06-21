package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Cart;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.CartNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Cart> findAllCarts() {
        return cartRepository.findAll();
    }

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCart(Long cartId) throws CartNotFoundException {
        return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
    }

    public void deleteCart(Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
        }
    }

    public void addProductToCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cartWithProductToAdd = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product productToAdd = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cartWithProductToAdd.getProducts().add(productToAdd);
        cartRepository.save(cartWithProductToAdd);
    }

    public void removeProductFromCart(Long cartId, Long productId) throws CartNotFoundException, ProductNotFoundException {
        Cart cartWithProductsToRemove = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        Product productToRemove = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cartWithProductsToRemove.getProducts().remove(productToRemove);
        cartRepository.save(cartWithProductsToRemove);
    }
}

