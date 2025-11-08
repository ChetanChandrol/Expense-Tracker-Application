package com.dev.Splitwise.service;

import com.dev.Splitwise.dto.GroupCreationRequestDto;
import com.dev.Splitwise.entity.Group;
import com.dev.Splitwise.entity.SettlementTransaction;
import com.dev.Splitwise.entity.User;
import com.dev.Splitwise.repository.GroupRepository;
import com.dev.Splitwise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImplementation implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<SettlementTransaction> settleUp(int groupId) {
        return List.of();
    }

    @Override
    public Group createGroup(GroupCreationRequestDto groupCreationRequestDto, int userId) {

        User currentUser = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User does not exist with id " + userId));


        Group newGroup = new Group();
        newGroup.setCreatedBy(currentUser);
        newGroup.setName(groupCreationRequestDto.getGroupName());

        List<User> members = new ArrayList<>();

        for (int memberId : groupCreationRequestDto.getMemberId()) {
            User member = userRepository.findById(memberId).orElseThrow(
                    () -> new RuntimeException("User does not exist with id " + memberId));
            members.add(member);
        }
        newGroup.setMembers(members);
        Group savedGroup = groupRepository.save(newGroup);

        for (User member : members) {
            if (member.getGroups() == null) {
                member.setGroups(new ArrayList<>());
            }
            member.getGroups().add(savedGroup);

            userRepository.save(member);
        }
        return savedGroup;
    }

    @Override
    public Boolean deleteGroup(int groupId) {
        groupRepository.deleteById(groupId);
        return true;
    }

    @Override
    public Group getGroup(int groupId) {
        return groupRepository.findById(groupId).orElseThrow(
                () -> new RuntimeException("Group does not exist with group id :" + groupId)
        );
    }

    @Override
    public Group updateGroup(int groupId) {
        return null;
    }
}
