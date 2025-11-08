package com.dev.Splitwise.controller;

import com.dev.Splitwise.dto.UserLoginRequestDTO;
import com.dev.Splitwise.dto.UserLoginResponseDTO;
import com.dev.Splitwise.dto.UserRegistrationRequestDto;
import com.dev.Splitwise.entity.User;
import com.dev.Splitwise.exception.UserInvallidPasswordException;
import com.dev.Splitwise.exception.UserRegitrationInvalidDataException;
import com.dev.Splitwise.mapper.EntityDtoMapper;
import com.dev.Splitwise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
       User savedUser = userService.signup(userRegistrationRequestDto.getName(), userRegistrationRequestDto.getEmail(), userRegistrationRequestDto.getPassword());
        return ResponseEntity.ok(EntityDtoMapper.toDo(savedUser));
    }

    public void validateUserRegistrationRequestDTo(UserRegistrationRequestDto RequestDto) {
        //VALIDATE IF EMAIL IS PROPER USING regex
        //VALIDATE IF PASSWORD ATLEAST 8 CHARACTERS INCLUDING BIG SMALL LETTERS USING REGEX
        if (RequestDto.getEmail() == null || RequestDto.getName() == null || RequestDto.getPassword() == null) {
            throw new UserRegitrationInvalidDataException("Invalid signUp of data");

        }
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLoginRequestDTO userLoginRequestDTODTO) {
        validateUserLoginRequestDTo(userLoginRequestDTODTO);
        User savedUser = userService.login(userLoginRequestDTODTO.getEmail(), userLoginRequestDTODTO.getPassword());
        return ResponseEntity.ok(EntityDtoMapper.toDo(savedUser));
    }

    public void validateUserLoginRequestDTo(UserLoginRequestDTO RequestDto) {
        //VALIDATE IF EMAIL IS PROPER USING regex
        //VALIDATE IF PASSWORD ATLEAST 8 CHARACTERS INCLUDING BIG SMALL LETTERS USING REGEX
        if (RequestDto.getEmail() == null ||  RequestDto.getPassword() == null) {
            throw new UserInvallidPasswordException("Invalid Password");

        }
    }
}