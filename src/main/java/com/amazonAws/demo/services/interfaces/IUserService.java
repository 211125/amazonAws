package com.amazonAws.demo.services.interfaces;

import com.amazonAws.demo.dto.request.CreateUserRequest;
import com.amazonAws.demo.dto.request.UpdateUserRequest;
import com.amazonAws.demo.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    UserResponse getUser(Long id);

    void create(CreateUserRequest request);

    List<UserResponse> list();

    void delete(Long id);

    UserResponse update(UpdateUserRequest request, Long id);
}
