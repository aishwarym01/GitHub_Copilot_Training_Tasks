package com.app.credit_card_management.dto;

import lombok.Data;

@Data
public class UserProfileUpdateDTO {
    private String newUsername;
    private String newEmail;
}
