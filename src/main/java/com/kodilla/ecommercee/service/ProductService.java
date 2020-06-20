package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.repository.GroupRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupRepository groupRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    public boolean doesProductExist(Long productId) {
        return productRepository.existsById(productId);
    }

    public void setProductGroup(Long productId, Long groupId) throws ProductNotFoundException, GroupNotFoundException {
        Product fetchedProduct = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        Group fetchedGroup = groupRepository.findById(groupId).orElseThrow(GroupNotFoundException::new);

        fetchedProduct.setGroup(fetchedGroup);
        fetchedGroup.getProducts().add(fetchedProduct);

        groupRepository.save(fetchedGroup);
        productRepository.save(fetchedProduct);
    }






}
