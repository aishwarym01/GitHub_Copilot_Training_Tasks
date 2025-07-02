//package com.app.credit_card_management.serviceimplementation;
//
//import com.app.credit_card_management.dto.PasswordUpdateRequestDTO;
//import com.app.credit_card_management.entity.User;
//import com.app.credit_card_management.repository.UserRepository;
//import com.app.credit_card_management.serviceinterfaces.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserService implements IUserService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    
//    @Override
//    public User getUserByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public void updatePassword(String username, PasswordUpdateRequestDTO request) {
//        User user = getUserByUsername(username);
//
//        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
//            throw new IllegalArgumentException("Current password is incorrect");
//        }
//
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        userRepository.save(user);
//    }
//    
//    @Override
//    public void updateUserProfile(String currentUsername, UserProfileUpdateDTO dto) {
//        User user = getUserByUsername(currentUsername);
//
//        // Check if new username is taken by someone else
//        if (!user.getUsername().equals(dto.getNewUsername()) &&
//            userRepository.findByUsername(dto.getNewUsername()).isPresent()) {
//            throw new IllegalArgumentException("Username already exists. Please choose another one.");
//        }
//
//        // Optional: Check if new email is taken
//        if (!user.getEmail().equals(dto.getNewEmail()) &&
//            userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(dto.getNewEmail()))) {
//            throw new IllegalArgumentException("Email already in use. Please use a different email.");
//        }
//
//        user.setUsername(dto.getNewUsername());
//        user.setEmail(dto.getNewEmail());
//        userRepository.save(user);
//    }
//
//    
//}


package com.app.credit_card_management.serviceimplementation;

import com.app.credit_card_management.dto.PasswordUpdateRequestDTO;
import com.app.credit_card_management.dto.UserProfileUpdateDTO;
import com.app.credit_card_management.entity.User;
import com.app.credit_card_management.repository.UserRepository;
import com.app.credit_card_management.serviceinterfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updatePassword(String username, PasswordUpdateRequestDTO request) {
        User user = getUserByUsername(username);

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public void updateUserProfile(String currentUsername, UserProfileUpdateDTO dto) {
        User user = getUserByUsername(currentUsername);

        if (!user.getUsername().equals(dto.getNewUsername()) &&
            userRepository.findByUsername(dto.getNewUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists. Please choose another one.");
        }

        if (!user.getEmail().equals(dto.getNewEmail()) &&
            userRepository.findByEmail(dto.getNewEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use. Please use a different email.");
        }

        user.setUsername(dto.getNewUsername());
        user.setEmail(dto.getNewEmail());
        userRepository.save(user);
    }
}

