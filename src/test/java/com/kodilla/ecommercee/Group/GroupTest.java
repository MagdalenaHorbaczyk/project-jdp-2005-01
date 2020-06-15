package com.kodilla.ecommercee.Group;


import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.entity.Product;
import com.kodilla.ecommercee.repository.GroupRepository;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupTest{
    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void SaveGroup() {
        //Given
        Group TestGroup = new Group(0L, "masło", null);
        //When
        groupRepository.save(TestGroup);
        Long TestGroupId = TestGroup.getGroupId();
        //Then
        Assert.assertEquals(0, (long) TestGroupId);
    }

    @Test
    public void SaveGroupAndProducts() {
        //Given
        Product TestGroupFirst = new Product();
        Product TestGroupSecond = new Product();
        List<Product> products = new ArrayList<>();
        products.add(TestGroupFirst);
        products.add(TestGroupSecond);
        Group TestGroup = new Group(3L, "masło", products);
        //When
        groupRepository.save(TestGroup);
        Long TestGroupId = TestGroup.getGroupId();
        int ListSize = groupRepository.findById(TestGroupId).get().getProducts().size();
        //Then
        Assert.assertEquals(2, ListSize);
        groupRepository.deleteById(TestGroupId);
    }

    @Test
    public void FindById() {
        //Given
        Group TestGroupFirst = new Group(0L, "masło", null);
        Group TestGroupSecond = new Group(2L, "masło", null);
        groupRepository.save(TestGroupFirst);
        Long TestGroupFirstId = TestGroupFirst.getGroupId();
        groupRepository.save(TestGroupSecond);
        //When
        Optional<Group> groupFound = groupRepository.findById(TestGroupFirstId);
        //Then
        Assert.assertTrue(groupFound.isPresent());
    }

    @Test
    public void FindAll() {
        //Given
        Group TestGroupFirst = new Group(0L, "masło", null);
        Group TestGroupSecond = new Group(1L, "masło", null);
        groupRepository.save(TestGroupFirst);
        groupRepository.save(TestGroupSecond);
        //When
        List<Group> Groups = groupRepository.findAll();
        //Then
        Assert.assertEquals(2, Groups.size());
    }

    @Test
    public void DeleteGroup() {
        //Given
        Group TestGroupFirst = new Group(0L, "masło", null);
        Group TestGroupSecond = new Group(3L, "masło", null);
        groupRepository.save(TestGroupFirst);
        Long groupOneId = TestGroupFirst.getGroupId();
        groupRepository.save(TestGroupSecond);
        //When
        groupRepository.deleteById(groupOneId);
        Optional<Group> Group = groupRepository.findById(groupOneId);
        //Then
        Assert.assertFalse(Group.isPresent());
    }
}
