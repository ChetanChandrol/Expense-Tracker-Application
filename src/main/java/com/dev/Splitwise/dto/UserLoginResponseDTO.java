package com.dev.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserLoginResponseDTO {
    private int id;
    private String name;
    private String email;
    private List<UserFriendResponseDTO> friendlist;
    private List<GroupResponseDTO> groups;
}
