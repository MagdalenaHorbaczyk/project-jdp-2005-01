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



@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupPositionTestSuite {

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void SaveGroup() {
        Group TestGroup = new Group();
        List<Product> ProductList = new ArrayList<>();
        TestGroup.setProducts(ProductList);
        TestGroup.setName("Masełko");

        //When
        groupRepository.save(TestGroup);
        Long TestGroupId = TestGroup.getGroupId();

        //Then
        Assert.assertEquals(1, (long) TestGroupId);

        //Clean-up:
        groupRepository.deleteById(TestGroupId);
    }

    @Test
    public void FindGroup() {
        //Given
        Group TestGroupFirst = new Group();
        TestGroupFirst.setName("Telewizor");
        Group TestGroupSecond = new Group();
        TestGroupSecond.setName("Komputer");

        //When
        groupRepository.save(TestGroupFirst);
        Long TestGroupFirstId = TestGroupFirst.getGroupId();
        groupRepository.save(TestGroupSecond);
        Long TestGroupSecondId = TestGroupSecond.getGroupId();

        //Then
        Assert.assertTrue(groupRepository.existsById(TestGroupFirstId));

        //Clean-up:
        groupRepository.deleteById(TestGroupFirstId);
        groupRepository.deleteById(TestGroupSecondId);
    }

    @Test
    public void DeleteGroup() {

        //Given
        Group TestGroupFirst = new Group();
        TestGroupFirst.setName("Telewizor");
        groupRepository.save(TestGroupFirst);

        //When
        Long TestGroupFirstId = TestGroupFirst.getGroupId();

        //Then
        groupRepository.deleteById(TestGroupFirstId);
        Assert.assertFalse(groupRepository.existsById(TestGroupFirstId));
    }
}

