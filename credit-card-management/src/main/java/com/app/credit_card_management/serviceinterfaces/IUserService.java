package com.app.credit_card_management.serviceinterfaces;

import com.app.credit_card_management.dto.PasswordUpdateRequestDTO;
import com.app.credit_card_management.dto.UserProfileUpdateDTO;
import com.app.credit_card_management.entity.User;
import java.util.List;

public interface IUserService {
    User getUserByUsername(String username);
    List<User> getAllUsers();
	void updatePassword(String username, PasswordUpdateRequestDTO request);
	void updateUserProfile(String currentUsername, UserProfileUpdateDTO dto);
}
