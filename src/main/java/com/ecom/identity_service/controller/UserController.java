package com.ecom.identity_service.controller;

import com.ecom.identity_service.dto.request.UserCreationRequest;
import com.ecom.identity_service.dto.request.UserUpdationRequest;
import com.ecom.identity_service.dto.request.ApiResponse;
import com.ecom.identity_service.dto.response.UserResponse;
import com.ecom.identity_service.entity.User;
import com.ecom.identity_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping()
    public List<User> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return this.userService.getUserById(userId);
    }

    @PostMapping()
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setResult(this.userService.createUser(request));

        return apiResponse;
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable("userId") String userId,
                           @RequestBody UserUpdationRequest request) {
        return this.userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        this.userService.deleteUser(userId);
        return "delete successfully " + userId;
    }
}
