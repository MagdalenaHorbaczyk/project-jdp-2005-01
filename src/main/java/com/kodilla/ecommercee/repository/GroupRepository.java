package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.entity.Group;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Override
    Group save(Group group);

    void deleteById(Long id);

    @Override
    List<Group> findAll();

    @Override
    Optional<Group> findById(final Long id);


}