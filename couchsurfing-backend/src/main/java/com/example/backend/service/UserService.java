package com.example.backend.service;

import com.example.backend.exception.EntityNotFoundException;
import com.example.backend.model.User;
import com.example.backend.model.enums.Authority;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;


    public Page<User> findHosts(String location, int page, int size) {
        return userRepository.findUsersByAuthoritiesContainingAndUserInfoLocation(
                List.of(Authority.ROLE_HOST),
                location,
                PageRequest.of(page, size)
        );
    }

    public Page<User> findSurfers(String location, int page, int size) {
        return userRepository.findUsersByAuthoritiesContainingAndUserInfoLocation(
                List.of(Authority.ROLE_SURFER),
                location,
                PageRequest.of(page, size)
        );
    }


    public User findUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public Page<User> findUsersByLocation(String location, int page, int size) {
        return userRepository.findUsersByUserInfoLocation(location, PageRequest.of(page, size));
    }

    @Transactional
    public User updateUser(User userToUpdate, String userId) {
        User userFromDb = findUserById(userId);

        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(userToUpdate, userFromDb);

        if (userToUpdate.getUserHome() != null && !userFromDb.getAuthorities().contains(Authority.ROLE_HOST))
            userFromDb.getAuthorities().add(Authority.ROLE_HOST);

        return save(userFromDb);
    }

    @Transactional
    public User save(User userToAdd) {
        return userRepository.save(userToAdd);
    }
}
