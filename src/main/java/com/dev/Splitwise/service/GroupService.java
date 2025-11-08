package com.dev.Splitwise.service;

import com.dev.Splitwise.dto.GroupCreationRequestDto;
import com.dev.Splitwise.entity.Group;
import com.dev.Splitwise.entity.SettlementTransaction;

import java.util.List;

public interface GroupService {
    List<SettlementTransaction> settleUp(int groupId);

    Group createGroup (GroupCreationRequestDto groupCreationRequestDto, int userId);

    Boolean deleteGroup(int groupId);

    Group getGroup (int groupId );

    Group updateGroup(int groupId);

}
