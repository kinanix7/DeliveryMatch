package com.deliverymatch.dto.auth;

import com.deliverymatch.entity.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private UserRole role;
}

