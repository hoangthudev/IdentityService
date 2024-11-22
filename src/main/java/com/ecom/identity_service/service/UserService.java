package com.ecom.identity_service.service;

import com.ecom.identity_service.dto.request.UserCreationRequest;
import com.ecom.identity_service.dto.request.UserUpdationRequest;
import com.ecom.identity_service.dto.response.UserResponse;
import com.ecom.identity_service.entity.User;
import com.ecom.identity_service.exception.AppException;
import com.ecom.identity_service.exception.ErrorCode;
import com.ecom.identity_service.mapper.UserMapper;
import com.ecom.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;

    UserMapper userMapper;

    public User createUser(UserCreationRequest request) {

        if (this.userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        //        User user = new User();
        //        user.setUsername(request.getUsername());
        //        user.setPassword(request.getPassword());
        //        user.setFirstName(request.getFirstName());
        //        user.setLastName(request.getLastName());
        //        user.setBirthDate(request.getBirthDate());

        //        Dung lombok
        //        annotation @Builder se giup build object nhanh hon
        //        UserCreationRequest request1 = UserCreationRequest.builder()
        //                .username(request.getUsername())
        //                .password(request.getPassword())
        //                .firstName(request.getFirstName())
        //                .lastName(request.getLastName())
        //                .birthDate(request.getBirthDate())
        //                .build();

        //        Dung mapstruct
        User user = this.userMapper.toUser(request);

        return this.userRepository.save(user);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public UserResponse getUserById(String userId) {
        return this.userMapper.toUserResponse(this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("create user not found")));
    }

    public UserResponse updateUser(String userId, UserUpdationRequest request) {
//        User user = this.getUserById(userId);
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setBirthDate(request.getBirthDate());
//        user.setPassword(request.getPassword());


        // dung maperstruct
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("update user not found"));
        this.userMapper.updateUse(user, request);
        return this.userMapper.toUserResponse(this.userRepository.save(user));
    }

    public void deleteUser(String userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Delete user not found"));
        this.userRepository.delete(user);
    }
}
