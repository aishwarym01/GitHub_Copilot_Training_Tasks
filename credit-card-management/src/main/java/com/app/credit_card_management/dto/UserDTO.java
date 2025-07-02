package com.app.credit_card_management.dto;

import lombok.Data;
@Data
public class UserDTO {
    private String username;
    private String password;
    private String email; 
    private String role; // "USER" or "ADMIN"
}

