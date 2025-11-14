package com.dev.Splitwise.mapper;

import com.dev.Splitwise.dto.*;
import com.dev.Splitwise.entity.*;

import java.util.ArrayList;
import java.util.List;

public class EntityDtoMapper {
    public static UserLoginResponseDTO toUserLoginResponse(User user) {
        UserLoginResponseDTO ResponseDTO = new UserLoginResponseDTO();
        ResponseDTO.setId(user.getId());
        ResponseDTO.setName(user.getName());
        ResponseDTO.setEmail(user.getEmail());

        List<UserFriendResponseDTO> friendList = new ArrayList<>();
        if (user.getFriends() != null) {
            for (User friend : user.getFriends()) {
                friendList.add(toFriendDto(friend));
            }
            ResponseDTO.setFriendlist(friendList);
        }
        if (user.getGroups() != null) {
            List<GroupResponseDTO> groupResponseDTOS = new ArrayList<>();
            for (Group group : user.getGroups()) {
                groupResponseDTOS.add(toGroupResponseDTO(group));
            }
            ResponseDTO.setGroups(groupResponseDTOS);
        }
        return ResponseDTO;
    }

    public static UserFriendResponseDTO toFriendDto(User user) {
        UserFriendResponseDTO userFriendResponseDTO = new UserFriendResponseDTO();
        userFriendResponseDTO.setId(user.getId());
        userFriendResponseDTO.setName(user.getName());
        return userFriendResponseDTO;
    }

    public static GroupResponseDTO toGroupResponseDTO(Group group) {
        GroupResponseDTO groupResponseDTO = new GroupResponseDTO();
        groupResponseDTO.setGroupId(group.getId());
        groupResponseDTO.setGroupName(group.getName());
        return groupResponseDTO;
    }

    public static CreateGroupResponseDTO toCreateGroupResponseDTO(Group group) {
        CreateGroupResponseDTO createGroupResponseDTO = new CreateGroupResponseDTO();
        createGroupResponseDTO.setGroupId(group.getId());
        createGroupResponseDTO.setGroupName(group.getName());
        createGroupResponseDTO.setCreatedBy(toUserResponseDTO(group.getCreatedBy()));

        if (group.getMembers() != null && !group.getMembers().isEmpty()) {
            ArrayList<UserResponseDTO> userResponseDTOS = new ArrayList<>();
            for (User user : group.getMembers()) {
                userResponseDTOS.add(toUserResponseDTO(user));
            }
            createGroupResponseDTO.setMembers(userResponseDTOS);
        }
        return createGroupResponseDTO;
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUserId(user.getId());
        userResponseDTO.setUserName(user.getName());
        return userResponseDTO;
    }

    public static GetGroupResponseDTO toGetGroupResponseDTO(Group group)
    {
        GetGroupResponseDTO getGroupResponseDTO = new GetGroupResponseDTO();

        getGroupResponseDTO.setGroupId(group.getId());
        getGroupResponseDTO.setGroupName(group.getName());
        getGroupResponseDTO.setCreatedBy(toUserResponseDTO(group.getCreatedBy()));
        getGroupResponseDTO.setExpense(group.getExpense());
        getGroupResponseDTO.setSettlementTransactions(group.getSettlementTransactions());

        if (group.getMembers() != null && !group.getMembers().isEmpty()) {
            ArrayList<UserResponseDTO> userResponseDTOS = new ArrayList<>();
            for (User user : group.getMembers()) {
                userResponseDTOS.add(toUserResponseDTO(user));
            }
            getGroupResponseDTO.setMembers(userResponseDTOS);
        }
        return getGroupResponseDTO;
    }
    public static CreateExpenseResponseDTO toCreateExpenseResponseDTO(Expense expense) {
        CreateExpenseResponseDTO createExpenseResponseDTO = new CreateExpenseResponseDTO();
        createExpenseResponseDTO.setExpenseId(expense.getId());
        createExpenseResponseDTO.setDescription(expense.getDescription());
        createExpenseResponseDTO.setAmount(expense.getAmount());
        createExpenseResponseDTO.setAddedBy(toUserResponseDTO(expense.getAddedBy()));
        if (expense.getUserExpenses() != null || !expense.getUserExpenses().isEmpty()) {
            ArrayList<UserExpenseResponseDTO> userExpenseResponseDTOS = new ArrayList<>();
            for(UserExpense userExpense:expense.getUserExpenses())
            {
                userExpenseResponseDTOS.add(toUserExpenseResponseDTO(userExpense));
            }
            createExpenseResponseDTO.setUserExpense(userExpenseResponseDTOS);
        }
        return createExpenseResponseDTO;
    }

    public static UserExpenseResponseDTO toUserExpenseResponseDTO(UserExpense userExpense) {
        UserExpenseResponseDTO userExpenseResponseDTO = new UserExpenseResponseDTO();
        userExpenseResponseDTO.setUser(toUserResponseDTO(userExpense.getUser()));
        userExpenseResponseDTO.setAmount(userExpense.getAmount());
        userExpenseResponseDTO.setUserExpenseType(userExpense.getUserExpenseType());
        return userExpenseResponseDTO;
    }

    public static List<SettlementTransactionResponseDTO> toSettlementTransactionResponseDTO(List<SettlementTransaction> settlementTransaction)
    {
        ArrayList<SettlementTransactionResponseDTO> settlementTransactionResponseDtoList = new ArrayList<>();
        for(SettlementTransaction transaction : settlementTransaction) {
            SettlementTransactionResponseDTO settlementTransactionResponseDTO = new SettlementTransactionResponseDTO();
            settlementTransactionResponseDTO.setBorrower(toUserResponseDTO(transaction.getBorrower()));
            settlementTransactionResponseDTO.setLender(toUserResponseDTO(transaction.getLender()));
            settlementTransactionResponseDTO.setAmount(transaction.getAmount());
            settlementTransactionResponseDtoList.add(settlementTransactionResponseDTO);
        }

        return settlementTransactionResponseDtoList;

    }

}



