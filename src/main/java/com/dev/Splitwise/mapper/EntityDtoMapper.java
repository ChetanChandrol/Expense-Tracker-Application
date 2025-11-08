package com.dev.Splitwise.mapper;

import com.dev.Splitwise.dto.GroupResponseDTO;
import com.dev.Splitwise.dto.UserFriendResponseDTO;
import com.dev.Splitwise.dto.UserLoginResponseDTO;
import com.dev.Splitwise.entity.Group;
import com.dev.Splitwise.entity.User;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoMapper {
    public static UserLoginResponseDTO toDo(User user)
    {
        UserLoginResponseDTO ResponseDTO = new UserLoginResponseDTO();
        ResponseDTO.setId(user.getId());
        ResponseDTO.setName(user.getName());
        ResponseDTO.setEmail(user.getEmail());

        List<UserFriendResponseDTO> friendList = new ArrayList<>();
        if(user.getFriends()!=null) {
            for (User friend : user.getFriends()) {
                friendList.add(toFriendDto(friend));
            }
            ResponseDTO.setFriendlist(friendList);
        }
        if (user.getGroups()!=null) {
            List<GroupResponseDTO> groups = new ArrayList<>();
            for (Group group : user.getGroups()) {
                groups.add(toDTO(group));
            }
            ResponseDTO.setGroups(groups);
        }
        return ResponseDTO;
    }

    public static UserFriendResponseDTO toFriendDto(User user){
        UserFriendResponseDTO userFriendResponseDTO = new UserFriendResponseDTO();
        userFriendResponseDTO.setId(user.getId());
        userFriendResponseDTO.setName(user.getName());
        return userFriendResponseDTO;
    }

    public static GroupResponseDTO toDTO (Group group)
    {
        return null;
    }


}
