package com.kodilla.ecommercee.entity;

import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTestSuite {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CartRepository cartRepository;

    @Test
    public void saveProductTest() {
        //Given
        Product product1 = new Product();
        product1.setName("Koszula");
        Product product2 = new Product();
        product2.setName("Spodnie");
        //When;
        productRepository.save(product1);
        productRepository.save(product2);
        Long product1Id = product1.getId();
        Long product2Id = product2.getId();
        //Then
        Assert.assertNotNull(product1Id);
        Assert.assertNotNull(product2Id);
        //Clean-up
        productRepository.deleteById(product1Id);
        productRepository.deleteById(product2Id);
    }
    @Test
    public void deleteProductTest() {
        //Given
        Product product1 = new Product();
        product1.setName("Koszula");
        productRepository.save(product1);
        Long product1Id = product1.getId();
        //When
        productRepository.deleteById(product1Id);
        //Then
        Assert.assertFalse(productRepository.existsById(product1Id));
    }
    @Test
    public void updateProductTest() {
        //Given
        Product product3 = new Product();
        product3.setName("Koszula");
        productRepository.save(product3);
        String name = product3.getName();
        Long product3Id = product3.getId();
        Product position = productRepository.findById(product3Id).orElse(null);
        //When
        assert position != null;
        position.setName("Koszula z długim rękawem");
        productRepository.save(position);
        String updatedName = position.getName();
        //When
        Assert.assertNotEquals(name, updatedName);
        //Clean-up
        productRepository.deleteById(product3Id);
    }
    @Test
    public void getProductTest() {
        //Given
        Product product4 = new Product();
        product4.setName("Płaszcz");
        //When
        productRepository.save(product4);
        Long product4Id = product4.getId();
        //Then
        Assert.assertNotNull(product4Id);
        //Clean-up
        productRepository.deleteById(product4Id);
    }
    @Test
    public void getAllProductsTest() {
        //Given
        Product product5 = new Product();
        product5.setName("Marynarka");
        productRepository.save(product5);

        Product product6 = new Product();
        product6.setName("Spodnie jeans");
        productRepository.save(product6);

        Product product7 = new Product();
        product7.setName("Kurtka");
        productRepository.save(product7);
        //When
        List<Product> listProducts = productRepository.findAll();
        //Then
        Assert.assertEquals(3, listProducts.size());
        //Clean-up
        productRepository.deleteAll();
    }
    @Test
    public void productInGroupAndCartTest() {
        //Given
        Product product8 = new Product();
        product8.setName("Koszula");
        productRepository.save(product8);
        Product product9 = new Product();
        product9.setName("Spodnie");
        productRepository.save(product9);
        Cart cart1 = new Cart();
        cartRepository.save(cart1);
        Group group1 = new Group();
        group1.setName("Odzież");
        groupRepository.save(group1);
        //When
        cart1.getProducts().add(product8);
        cart1.getProducts().add(product9);
        product8.getCarts().add(cart1);
        product9.getCarts().add(cart1);
        cartRepository.save(cart1);
        productRepository.save(product8);
        productRepository.save(product9);

        group1.getProducts().add(product8);
        group1.getProducts().add(product9);
        groupRepository.save(group1);

        Long product8Id = product8.getId();
        Long product9Id = product9.getId();
        Long group1Id = group1.getGroupId();
        Long cart1Id = cart1.getCartId();
        //Then
        Assert.assertEquals(2,productRepository.count());
        Assert.assertEquals(1,groupRepository.count());
        Assert.assertEquals(1,cartRepository.count());
        Assert.assertNotNull(product8Id);
        Assert.assertNotNull(product9Id);
        Assert.assertNotNull(cart1Id);
        //Clean-up
        productRepository.deleteAll();
        groupRepository.deleteById(group1Id);
        cartRepository.deleteAll();
    }
    @Test
    public void deleteCartTest() {
        //Given
        Product product9 = new Product();
        product9.setName("Koszula");
        productRepository.save(product9);
        Cart cart = new Cart();
        cartRepository.save(cart);

        product9.getCarts().add(cart);
        cart.getProducts().add(product9);
        cartRepository.save(cart);
        productRepository.save(product9);

        Long product9Id = product9.getId();
        Long cartId = cart.getCartId();
        //When
        cartRepository.deleteById(cartId);
        //Then
        Assert.assertFalse(cartRepository.existsById(cartId));
        Assert.assertFalse(productRepository.existsById(product9Id));
    }
    @Test
    public void deleteGroupTest() {
        //Given
        Product product10 = new Product();
        product10.setName("Koszula");
        Group group = new Group();
        group.setName("Odzież");
        productRepository.save(product10);
        groupRepository.save(group);

        group.getProducts().add(product10);
        groupRepository.save(group);

        Long product10Id = product10.getId();
        Long groupId = group.getGroupId();
        //When
        groupRepository.deleteById(groupId);
        //Then
        Assert.assertFalse(groupRepository.existsById(groupId));
        Assert.assertTrue(productRepository.existsById(product10Id));
        //Clean-up
        productRepository.deleteById(product10Id);
    }

    @Test
    public void deleteProductFromCartAndGroupTest() {
        //Given
        Product product = new Product();
        product.setName("Koszula");
        Group group = new Group();
        group.setName("Odzież");
        Cart cart = new Cart();
        productRepository.save(product);
        groupRepository.save(group);
        cartRepository.save(cart);

        group.getProducts().add(product);
        product.setGroup(group);
        product.getCarts().add(cart);
        cart.getProducts().add(product);
        productRepository.save(product);
        groupRepository.save(group);
        cartRepository.save(cart);

        Long productId = product.getId();
        Long groupId = group.getGroupId();
        Long cartId = cart.getCartId();
        //When
        productRepository.deleteById(productId);
        //Then
        Assert.assertFalse(productRepository.existsById(productId));
        Assert.assertTrue(groupRepository.existsById(groupId));
        Assert.assertFalse(cartRepository.existsById(cartId));
        //Clean-up
        groupRepository.deleteById(groupId);
    }
}


