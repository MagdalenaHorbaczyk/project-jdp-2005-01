package com.kodilla.ecommercee.repository;

import java.util.List;
import java.util.Optional;

import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    Group save(Group group);

    void deleteById(Long Id);

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(final Long Id);

}