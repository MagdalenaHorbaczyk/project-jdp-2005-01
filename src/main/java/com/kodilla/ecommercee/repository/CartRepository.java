package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartRepository extends CrudRepository<Cart, Long> {

    @Override
    public List<Cart> findAll();

    @Override
    public Cart save(Cart cart);

    @Override
    public Optional<Cart> findById(Long cartId);
}
