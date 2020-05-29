package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/group")
public class GroupController {


    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public GroupDto getGroup(@RequestParam Long groupId) {
        return new GroupDto(1010L, "AGD");
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<GroupDto> getGroups(){
        GroupDto dummyGroupDtoA = new GroupDto(900L, "Obuwie");
        GroupDto dummyGroupDtoB = new GroupDto(800L, "Biżuteria");
        List<GroupDto> dummyGroupDtoList = new ArrayList<>();
        dummyGroupDtoList.add(dummyGroupDtoA);
        dummyGroupDtoList.add(dummyGroupDtoB);
        return dummyGroupDtoList;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup")
    public void createGroup(GroupDto groupDto){
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup")
    public GroupDto updateGroup(GroupDto groupDto){
        return new GroupDto(700L, "updated Group");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(Long groupId){

    }


}
