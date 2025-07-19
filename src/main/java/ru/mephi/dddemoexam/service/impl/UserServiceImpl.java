package ru.mephi.dddemoexam.service.impl;

import lombok.RequiredArgsConstructor;
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
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserResponse convertToResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }

    @Override
    public UserResponse createUser(UserRequest request) {
        var user = modelMapper.map(request, User.class);
        return convertToResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getUsersByMinAge(Integer age) {
        return userRepository.findByAgeGreaterThanEqualOrderByFirstNameAsc(age)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

}