package com.app.credit_card_management.dto;

import lombok.Data;

@Data
public class PasswordUpdateRequestDTO {
    private String currentPassword;
    private String newPassword;

}
