package com.ciphers.userprofilemanagement.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ciphers.userprofilemanagement.dto.UserProfileDTO;
import com.ciphers.userprofilemanagement.entity.UserProfile;
import com.ciphers.userprofilemanagement.exception.DuplicateEmailException;
import com.ciphers.userprofilemanagement.exception.ResourceNotFoundException;
import com.ciphers.userprofilemanagement.repository.UserProfileRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    public List<UserProfileDTO> getAll() {
        return repository.findAll().stream().map(this::convertToDTO).toList();
    }

    public UserProfileDTO getById(Long id) {
        return repository.findById(id).map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    
    public UserProfileDTO save(UserProfileDTO dto) {
        if (dto.getId() == null && repository.existsByEmail(dto.getEmail())) {
            throw new DuplicateEmailException("Email already exists: " + dto.getEmail());
        }

        UserProfile entity = convertToEntity(dto);
        UserProfile saved = repository.save(entity);
        return convertToDTO(saved);
    }


    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id " + id);
        }
        repository.deleteById(id);
    }

    private UserProfileDTO convertToDTO(UserProfile entity) {
        UserProfileDTO dto = new UserProfileDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }

    private UserProfile convertToEntity(UserProfileDTO dto) {
        UserProfile entity = new UserProfile();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }
}
