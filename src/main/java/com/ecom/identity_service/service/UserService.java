package com.ecom.identity_service.service;

import com.ecom.identity_service.dto.request.UserCreationRequest;
import com.ecom.identity_service.dto.request.UserUpdationRequest;
import com.ecom.identity_service.entity.User;
import com.ecom.identity_service.exception.AppException;
import com.ecom.identity_service.exception.ErrorCode;
import com.ecom.identity_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        return this.userRepository.save(user);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserById(String userId) {
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("create user not found"));
    }

    public User updateUser(String userId, UserUpdationRequest request) {
        User user = this.getUserById(userId);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setBirthDate(request.getBirthDate());
        user.setPassword(request.getPassword());
        return this.userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = this.getUserById(userId);
        this.userRepository.delete(user);
    }
}
