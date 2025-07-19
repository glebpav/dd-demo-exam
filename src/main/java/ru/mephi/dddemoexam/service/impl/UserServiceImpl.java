package ru.mephi.dddemoexam.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.mephi.dddemoexam.dto.UserRequest;
import ru.mephi.dddemoexam.dto.UserResponse;
import ru.mephi.dddemoexam.model.User;
import ru.mephi.dddemoexam.repository.UserRepository;
import ru.mephi.dddemoexam.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserResponse convertToResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        log.debug("Fetching all users from database");
        try {
            return userRepository.findAll().stream()
                    .map(user -> {
                        UserResponse response = convertToResponse(user);
                        log.trace("Mapped user: {}", response);
                        return response;
                    })
                    .toList();
        } catch (Exception e) {
            log.error("Database error while fetching users", e);
            throw e;
        }
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        log.debug("Creating user with data: {}", request);
        try {
            var user = modelMapper.map(request, User.class);
            User savedUser = userRepository.save(user);
            log.info("Saved user with ID: {}", savedUser.getId());
            return convertToResponse(savedUser);
        } catch (Exception e) {
            log.error("Database error while creating user", e);
            throw e;
        }
    }

    @Override
    public List<UserResponse> getUsersByMinAge(Integer age) {
        log.debug("Searching users with min age: {}", age);
        try {
            return userRepository.findByAgeGreaterThanEqualOrderByFirstNameAsc(age)
                    .stream()
                    .map(user -> {
                        UserResponse response = convertToResponse(user);
                        log.trace("Filtered user: {}", response);
                        return response;
                    })
                    .toList();
        } catch (Exception e) {
            log.error("Database error while searching by age", e);
            throw e;
        }
    }

}