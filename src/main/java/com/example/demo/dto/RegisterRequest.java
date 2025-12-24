package com.example.demo.dto;

import com.example.demo.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
    private Role role;
}
