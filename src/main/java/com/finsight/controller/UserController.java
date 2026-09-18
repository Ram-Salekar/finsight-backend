package com.finsight.controller;

import com.finsight.apimodel.UserRequest;
import com.finsight.apimodel.UserResponse;
import com.finsight.apitomanagermapper.UserApiMapper;
import com.finsight.manager.IUserManager;
import com.finsight.managermodel.UserManagerModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userApiMapper.toResponse(userManager.getUserById(id));
    }

    @PostMapping
    public UserResponse registerUser(@Valid @RequestBody UserRequest request) {
        UserManagerModel model = userApiMapper.toManagerModel(request);
        // request.getPassword() is the one raw password value in this whole
        // flow — passed separately into the manager, which hashes it before
        // anything touches the database.
        UserManagerModel created = userManager.registerUser(model, request.getPassword());
        return userApiMapper.toResponse(created);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userManager.deleteUser(id);
    }
}
