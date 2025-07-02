//package com.app.credit_card_management.controller;
//
//import com.app.credit_card_management.dto.PasswordUpdateRequestDTO;
//import com.app.credit_card_management.serviceimplementation.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.security.Principal;
//
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PutMapping("/profile/password")
//    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequestDTO request,
//                                                 Principal principal) {
//        userService.updatePassword(principal.getName(), request);
//        return ResponseEntity.ok("Password updated successfully");
//    }
//}


package com.app.credit_card_management.controller;

import com.app.credit_card_management.dto.PasswordUpdateRequestDTO;
import com.app.credit_card_management.dto.UserProfileUpdateDTO;
import com.app.credit_card_management.serviceimplementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/profile/password")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequestDTO request,
                                                 Principal principal) {
        userService.updatePassword(principal.getName(), request);
        return ResponseEntity.ok("Password updated successfully");
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateProfile(@RequestBody UserProfileUpdateDTO dto,
                                                Principal principal) {
        userService.updateUserProfile(principal.getName(), dto);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
