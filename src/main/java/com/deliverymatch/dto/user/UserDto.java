package com.deliverymatch.dto.user;

import com.deliverymatch.entity.UserRole;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRole role;
    private boolean verified;
    private boolean active;
    private LocalDateTime createdAt;
}

