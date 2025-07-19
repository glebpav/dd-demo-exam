package ru.mephi.dddemoexam.service;

import ru.mephi.dddemoexam.dto.UserRequest;
import ru.mephi.dddemoexam.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAllUsers();
    UserResponse createUser(UserRequest request);
    List<UserResponse> getUsersByMinAge(Integer age);

}
