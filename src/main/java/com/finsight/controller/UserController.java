package com.finsight.controller;

import com.finsight.apimodel.UserResponse;
import com.finsight.apitomanagermapper.UserApiMapper;
import com.finsight.manager.IUserManager;
import com.finsight.managermodel.UserManagerModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserManager userManager;
    private final UserApiMapper userApiMapper;

    public UserController(IUserManager userManager, UserApiMapper userApiMapper) {
        this.userManager = userManager;
        this.userApiMapper = userApiMapper;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        List<UserManagerModel> users = userManager.getAllUsers();
        return users.stream()
                .map(userApiMapper::toResponse)
                .collect(Collectors.toList());
    }
}
