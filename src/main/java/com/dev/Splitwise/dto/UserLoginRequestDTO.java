package com.dev.Splitwise.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequestDTO {
    private String email;
    private String password;
}
