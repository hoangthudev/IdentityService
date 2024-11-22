package com.ecom.identity_service.mapper;

import com.ecom.identity_service.dto.request.UserCreationRequest;
import com.ecom.identity_service.dto.request.UserUpdationRequest;
import com.ecom.identity_service.dto.response.UserResponse;
import com.ecom.identity_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// Su dung mapstruct
@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    // 2 filed cua 2 doi tuong khac nhau ma ta muon map chung lai
//    @Mapping(source = "firstName", target = "lastName") // source la cai ta lay con target la cai ta lay ve
//    @Mapping(target = "lastName", ignore = true)
    UserResponse toUserResponse(User user);

    // annotation mappingtarget se mapping request vao object user
    void updateUse(@MappingTarget User user, UserUpdationRequest request);
}
