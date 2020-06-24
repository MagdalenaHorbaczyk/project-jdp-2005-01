package com.kodilla.ecommercee.entity;
import com.kodilla.ecommercee.repository.CartRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTestSuite {
    @Autowired
    private CartRepository cartRepository;

    @Test
    public void saveCartTest(){
        //Given:
        List<Product> emptyProductsList = new ArrayList<>();
        Cart cart1 = new Cart();
        cart1.setProducts(emptyProductsList);
        Cart cart2 = new Cart();
        cart2.setProducts(emptyProductsList);

        //When:
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        Long cart1Id = cart1.getCartId();
        Long cart2Id = cart2.getCartId();

        //Then:
        Assert.assertNotNull(cart1Id);
        Assert.assertNotNull(cart2Id);

        //Clean-up:
        cartRepository.deleteById(cart1Id);
        cartRepository.deleteById(cart2Id);
    }

    @Test
    public void getAllCartsTest(){
        //Given:
        List<Product> emptyProductsList = new ArrayList<>();
        Cart cart1 = new Cart();
        cart1.setProducts(emptyProductsList);
        Cart cart2 = new Cart();
        cart2.setProducts(emptyProductsList);

        //When:
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        List<Cart> carts = cartRepository.findAll();

        //Then:
        Assert.assertEquals(2, carts.size());

        //Clean-up:
        cartRepository.deleteAll();
    }

    @Test
    public void findCartTest(){
        //Given:
        List<Product> emptyProductsList = new ArrayList<>();
        Cart cart1 = new Cart();
        cart1.setProducts(emptyProductsList);
        Cart cart2 = new Cart();
        cart2.setProducts(emptyProductsList);
        //When:
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        Long cart1Id = cart1.getCartId();
        Long cart2Id = cart2.getCartId();
        //Then:
        Assert.assertTrue(cartRepository.existsById(cart1Id));
        Assert.assertTrue(cartRepository.existsById(cart2Id));
        //Clean-up:
        cartRepository.deleteById(cart1Id);
        cartRepository.deleteById(cart2Id);
    }
    @Test
    public void deleteCartTest(){
        //Given:
        List<Product> emptyProductsList = new ArrayList<>();
        Cart cart1 = new Cart();
        cart1.setProducts(emptyProductsList);
        Cart cart2 = new Cart();
        cart2.setProducts(emptyProductsList);
        //When:
        cartRepository.save(cart1);
        cartRepository.save(cart2);
        Long cart1Id = cart1.getCartId();
        Long cart2Id = cart2.getCartId();
        //Then:
        //Clean-up:
        cartRepository.deleteById(cart1Id);
        cartRepository.deleteById(cart2Id);
        Assert.assertFalse(cartRepository.existsById(cart1Id));
        Assert.assertFalse(cartRepository.existsById(cart2Id));
    }
    @Test
    public void updateCartTest(){
        //Given:
        List<Product> emptyProductsList = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Product1");
        emptyProductsList.add(product1);
        Cart cart = new Cart();
        cart.setProducts(emptyProductsList);
        cartRepository.save(cart);
        Long cartId = cart.getCartId();
        List<Product> productList = cart.getProducts();
        Cart position = cartRepository.findById(cartId).orElse(null);
        //When:
        assert position != null;
        List<Product> updatedProductsList = new ArrayList<>();
        Product product2 = new Product();
        product2.setName("Product2");
        updatedProductsList.add(product2);
        position.setProducts(updatedProductsList);
        cartRepository.save(position);
        List<Product> updatedProductList = position.getProducts();
        //Then:
        Assert.assertNotEquals(productList, updatedProductList);
        //Clean-up:
        cartRepository.deleteById(cartId);
    }
}