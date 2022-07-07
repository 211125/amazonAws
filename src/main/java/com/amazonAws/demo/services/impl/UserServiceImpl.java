package com.amazonAws.demo.services.impl;

import com.amazonAws.demo.dto.request.CreateUserRequest;
import com.amazonAws.demo.dto.request.UpdateUserRequest;
import com.amazonAws.demo.dto.response.UserResponse;
import com.amazonAws.demo.entities.User;
import com.amazonAws.demo.repositories.IUserRepository;
import com.amazonAws.demo.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserResponse getUser(Long id) {
        Optional<User> userOptional = repository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse from = this.from(user);
            return from;
        }
        throw new RuntimeException("user not found");
    }

    @Override
    public void create(CreateUserRequest request) {
        User user = from(request);
        repository.save(user);
    }

    @Override
    public List<UserResponse> list() {
        List<User> users = repository.findAll();
        List<UserResponse> userResponses = from(users);
        return userResponses;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UserResponse update(UpdateUserRequest request, Long id) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User updated = from(request, user);
            User saved = repository.save(updated);
            UserResponse response = from(saved);
            return response;
        }
        throw new RuntimeException("user did not update");
    }

    private User from(UpdateUserRequest request, User user) {
        user.setName(user.getName());
        user.setLastName(user.getLastName());
        user.setAge(user.getAge());
        user.setPlateNumber(user.getPlateNumber());
        user.setCompanyName(user.getCompanyName());
        return user;
    }

    private List<UserResponse> from(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            UserResponse response = from(user);
            userResponses.add(response);
        }
        return userResponses;
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setName(user.getName());
        user.setLastName(user.getName());
        user.setAge(user.getAge());
        user.setCompanyName(user.getCompanyName());
        user.setPlateNumber(user.getPlateNumber());
        return user;
    }

    private UserResponse from(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setLastName(user.getLastName());
        response.setAge(user.getAge());
        response.setCompanyName(user.getCompanyName());
        response.setPlateNumber(user.getPlateNumber());
        return response;
    }
}
