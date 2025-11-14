package com.dev.Splitwise.controller;

import com.dev.Splitwise.dto.GroupCreationRequestDto;
import com.dev.Splitwise.entity.Group;
import com.dev.Splitwise.entity.SettlementTransaction;
import com.dev.Splitwise.mapper.EntityDtoMapper;
import com.dev.Splitwise.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/settleup/{groupId}")
    public ResponseEntity settleUp (@PathVariable("groupId") int groupId)
    {
        List <SettlementTransaction> settlementTransactions = groupService.settleUp(groupId);

        return ResponseEntity.ok(EntityDtoMapper.toSettlementTransactionResponseDTO(settlementTransactions));
    }

    @PostMapping("/create-group/{userId}")
    public ResponseEntity createGroup (@RequestBody GroupCreationRequestDto groupCreationRequestDto, @PathVariable("userId") int userId )
    {   //validation on name and list>1
        Group createdGroup = groupService.createGroup(groupCreationRequestDto,userId);
        return ResponseEntity.ok(EntityDtoMapper.toCreateGroupResponseDTO(createdGroup));
    }

    @PutMapping("/update-group/{groupId}")
    public ResponseEntity updateGroup (@PathVariable("groupId") int groupId)
    {

        return null;
    }

    @DeleteMapping("/delete-group/{groupId}")
    public ResponseEntity deleteGroup(@PathVariable("groupId") int groupId)
    {   groupService.deleteGroup(groupId);
        return ResponseEntity.ok("Group Deleted Successfully");
    }

    @GetMapping("/get-group/{groupId}")
    public ResponseEntity getGroup(@PathVariable("groupId") int groupId)
    {
        Group group = groupService.getGroup(groupId);
        return ResponseEntity.ok(EntityDtoMapper.toGetGroupResponseDTO(group));
    }







}
