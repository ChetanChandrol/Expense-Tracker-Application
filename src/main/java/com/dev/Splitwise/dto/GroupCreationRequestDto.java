package com.dev.Splitwise.dto;

import com.dev.Splitwise.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class GroupCreationRequestDto {
    private String groupName;
    private List<Integer> memberId;
}
