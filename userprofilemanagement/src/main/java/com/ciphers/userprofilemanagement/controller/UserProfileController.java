package com.ciphers.userprofilemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ciphers.userprofilemanagement.dto.UserProfileDTO;
import com.ciphers.userprofilemanagement.service.UserProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    private UserProfileService service;

    @GetMapping
    public List<UserProfileDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UserProfileDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public UserProfileDTO create(@Valid @RequestBody UserProfileDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/{id}")
    public UserProfileDTO update(@PathVariable Long id, @Valid @RequestBody UserProfileDTO dto) {
        dto.setId(id);
        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
