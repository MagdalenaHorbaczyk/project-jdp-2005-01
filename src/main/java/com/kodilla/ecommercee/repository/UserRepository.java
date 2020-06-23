package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    Optional<User> findById(Long id);



}
