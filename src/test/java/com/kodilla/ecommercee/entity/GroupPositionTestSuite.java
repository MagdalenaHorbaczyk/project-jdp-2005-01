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
    public void FindAllGroups() {
        //Given
        Group TestGroupFirst = new Group();
        TestGroupFirst.setName("Chlebek");
        Group TestGroupSecond = new Group();
        TestGroupSecond.setName("Bułeczka");
        groupRepository.save(TestGroupFirst);
        Long TestGroupId_One = TestGroupFirst.getGroupId();
        groupRepository.save(TestGroupSecond);
        Long TestGroupId_two = TestGroupSecond.getGroupId();

        //When
        List<Group> groups = groupRepository.findAll();

        //Then
        Assert.assertEquals(2, groups.size());

        //Clean-up:
        groupRepository.deleteById(TestGroupId_One);
        groupRepository.deleteById(TestGroupId_two);
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

