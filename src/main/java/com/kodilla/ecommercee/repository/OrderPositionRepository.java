package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.OrderPosition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface OrderPositionRepository extends CrudRepository<OrderPosition, Long> {
    @Override
   List<OrderPosition> findAll();
}
