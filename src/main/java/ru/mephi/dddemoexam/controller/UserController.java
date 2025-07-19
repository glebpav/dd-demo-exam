package ru.mephi.dddemoexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Users", description = "API for managing users")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Retrieve all users", description = "Returns a list of all available users")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user", description = "Creates a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public UserResponse createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/additional-info")
    @Operation(summary = "Filter by age", description = "Returns users older than the specified age")
    public List<UserResponse> getUsersByMinAge(@RequestParam Integer age) {
        return userService.getUsersByMinAge(age);
    }

}
