package com.kodilla.ecommercee.entity;


import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupPositionTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void SaveGroup() {
        //Given
        Group testGroup = new Group();
        List<Product> productList = new ArrayList<>();
        testGroup.setProducts(productList);
        testGroup.setName("Masełko");

        //When
        groupRepository.save(testGroup);
        Long testGroupId = testGroup.getGroupId();

        //Then
        assertEquals(1, (long) testGroupId);

        //Clean-up:
        groupRepository.deleteById(testGroupId);
    }

    @Test
    public void FindGroup() {
        //Given
        Group testGroupFirst = new Group();
        testGroupFirst.setName("Telewizor");
        Group testGroupSecond = new Group();
        testGroupSecond.setName("Komputer");

        //When
        groupRepository.save(testGroupFirst);
        Long testGroupFirstId = testGroupFirst.getGroupId();
        groupRepository.save(testGroupSecond);
        Long testGroupSecondId = testGroupSecond.getGroupId();

        //Then
        Assert.assertTrue(groupRepository.existsById(testGroupFirstId));

        //Clean-up:
        groupRepository.deleteById(testGroupFirstId);
        groupRepository.deleteById(testGroupSecondId);
    }

    @Test
    public void DeleteGroup() {
        //Given
        Group testGroupFirst = new Group();
        testGroupFirst.setName("Telewizor");
        groupRepository.save(testGroupFirst);

        //When
        Long testGroupFirstId = testGroupFirst.getGroupId();
        groupRepository.deleteById(testGroupFirstId);

        //Then
        Assert.assertFalse(groupRepository.existsById(testGroupFirstId));
    }

    @Test
    public void UpdateGroup(){
        //Given
        long zeroSaves = groupRepository.count();
        Group group = new Group();
        group.setName("Buciki");
        groupRepository.save(group);

        //When
        Group firstSave= new Group();
        firstSave.setGroupId(group.getGroupId());
        firstSave.setName("Adidasy");
        groupRepository.save(firstSave);

        //Then
        long nextNumOfRecords = groupRepository.count();
        assertEquals(1,nextNumOfRecords - zeroSaves);

        //Clean Up
        groupRepository.deleteById(group.getGroupId());
    }
}

