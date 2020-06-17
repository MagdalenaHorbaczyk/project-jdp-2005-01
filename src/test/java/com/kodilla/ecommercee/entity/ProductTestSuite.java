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
        System.out.println(name);
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
        String savedName = product4.getName();
        //Then
        Assert.assertNotNull(savedName);
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
        List<Product> listProducts = (List<Product>) productRepository.findAll();
        //Then
        Assert.assertEquals(3, listProducts.size());
        //Clean-up
        productRepository.deleteAll();
    }
    @Test
    public void productGroupCartTest() {
        //Given
        Product product8 = new Product();
        product8.setName("Koszula");
        Product product9 = new Product();
        product9.setName("Spodnie");
        Cart cart1 = new Cart();
        cartRepository.save(cart1);
        cart1.getProducts().add(product8);
        cart1.getProducts().add(product9);
        Group group1 = new Group();
        group1.setName("Odzież");
        groupRepository.save(group1);
        group1.getProducts().add(product8);
        group1.getProducts().add(product9);
        product8.setGroup(group1);
        product9.setGroup(group1);
        //When
        productRepository.save(product8);
        productRepository.save(product9);
        Long product8Id = product8.getId();
        Long product9Id = product9.getId();
        String group1Name = group1.getName();
        Long group1Id = group1.getGroupId();
        Long cart1Id = cart1.getCartId();
        //Then
        Assert.assertEquals(2,productRepository.count());
        Assert.assertNotNull(product8Id);
        Assert.assertNotNull(product9Id);
        Assert.assertEquals(1,groupRepository.count());
        Assert.assertEquals("Odzież", group1Name);
        Assert.assertNotNull(cart1Id);
        //Clean-up
        productRepository.deleteById(product8Id);
        productRepository.deleteById(product9Id);
        groupRepository.deleteById(group1Id);
        cartRepository.deleteById(cart1Id);
    }
    @Test
    public void deleteProductGroupCartTest() {
        //Given
        Product product10 = new Product();
        product10.setName("Koszula");
        Cart cart = new Cart();
        cartRepository.save(cart);
        Group group = new Group();
        group.setName("Odzież");
        groupRepository.save(group);
        group.getProducts().add(product10);
        cart.getProducts().add(product10);
        product10.setGroup(group);
        productRepository.save(product10);
        Long product10Id = product10.getId();
        Long groupId = group.getGroupId();
        Long cartId = cart.getCartId();
        //When
        cartRepository.deleteById(cartId);
        //Then
        Assert.assertFalse(cartRepository.existsById(cartId));
        Assert.assertTrue(groupRepository.existsById(groupId));
        Assert.assertTrue(productRepository.existsById(product10Id));
        //Clean-up
        productRepository.deleteById(product10Id);
        groupRepository.deleteById(groupId);
    }
    @Test
    public void getProductInCartTest(){
        //Given
        Product product12 = new Product();
        product12.setName("Koszula krótki rekaw");
        Product product13 = new Product();
        product13.setName("Spodnie wizytowe");
        Group group = new Group();
        group.setName("Odzież");
        groupRepository.save(group);
        product12.setGroup(group);
        product13.setGroup(group);
        Cart cart = new Cart();
        cart.getProducts().add(product12);
        cart.getProducts().add(product13);
        //When
        cartRepository.save(cart);
        Long id = cart.getCartId();
        //Then
        Assert.assertNotNull(id);
        //Clean-up
        cartRepository.deleteById(id);
        groupRepository.delete(group);
    }
}

