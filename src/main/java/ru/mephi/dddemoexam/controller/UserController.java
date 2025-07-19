package ru.mephi.dddemoexam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.mephi.dddemoexam.dto.UserRequest;
import ru.mephi.dddemoexam.dto.UserResponse;
import ru.mephi.dddemoexam.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user-api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/additional-info")
    public List<UserResponse> getUsersByMinAge(@RequestParam Integer age) {
        return userService.getUsersByMinAge(age);
    }

}
