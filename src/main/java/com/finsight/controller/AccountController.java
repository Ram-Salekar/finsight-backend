package com.finsight.controller;

import com.finsight.apimodel.AccountRequest;
import com.finsight.apimodel.AccountResponse;
import com.finsight.apitomanagermapper.AccountApiMapper;
import com.finsight.manager.IAccountManager;
import com.finsight.managermodel.AccountManagerModel;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final IAccountManager accountManager;
    private final AccountApiMapper accountApiMapper;

    public AccountController(IAccountManager accountManager, AccountApiMapper accountApiMapper) {
        this.accountManager = accountManager;
        this.accountApiMapper = accountApiMapper;
    }

    @GetMapping
    public List<AccountResponse> getAllAccounts() {
        return accountManager.getAllAccounts().stream()
                .map(accountApiMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountResponse getAccountById(@PathVariable Long id) {
        return accountApiMapper.toResponse(accountManager.getAccountById(id));
    }

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody AccountRequest request) {
        AccountManagerModel model = accountApiMapper.toManagerModel(request);
        return accountApiMapper.toResponse(accountManager.createAccount(model));
    }

    @PutMapping("/{id}")
    public AccountResponse updateAccount(@PathVariable Long id, @Valid @RequestBody AccountRequest request) {
        AccountManagerModel model = accountApiMapper.toManagerModel(request);
        return accountApiMapper.toResponse(accountManager.updateAccount(id, model));
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountManager.deleteAccount(id);
    }
}
