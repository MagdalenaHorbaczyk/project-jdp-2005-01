package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    public Product save(Product product);

    @Override
    public List<Product> findAll();

    @Override
    public Optional<Product> findById(Long productId);


}
