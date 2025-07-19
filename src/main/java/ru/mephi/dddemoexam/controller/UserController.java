package ru.mephi.dddemoexam.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @Operation(summary = "Retrieve all users", description = "Returns a list of all available users")
    public List<UserResponse> getAllUsers() {
        log.info("Fetching all users");
        try {
            List<UserResponse> users = userService.getAllUsers();
            log.info("Returning {} users", users.size());
            return users;
        } catch (Exception e) {
            log.error("Error fetching users", e);
            throw e;
        }
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a user", description = "Creates a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public UserResponse createUser(@RequestBody UserRequest request) {
        log.debug("Creating user with request: {}", request);
        try {
            UserResponse response = userService.createUser(request);
            log.info("User created with ID: {}", response.getId());
            return response;
        } catch (Exception e) {
            log.error("Error creating user", e);
            throw e;
        }
    }

    @GetMapping("/additional-info")
    @Operation(summary = "Filter by age", description = "Returns users older than the specified age")
    public List<UserResponse> getUsersByMinAge(@RequestParam Integer age) {
        log.info("Fetching users with min age: {}", age);
        try {
            List<UserResponse> users = userService.getUsersByMinAge(age);
            log.info("Found {} users by min age: {}", users.size(), age);
            return users;
        } catch (Exception e) {
            log.error("Error fetching users by min age", e);
            throw e;
        }
    }

}
