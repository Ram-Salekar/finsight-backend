package com.finsight.controller;

import com.finsight.apimodel.BudgetRequest;
import com.finsight.apimodel.BudgetResponse;
import com.finsight.apitomanagermapper.BudgetApiMapper;
import com.finsight.manager.IBudgetManager;
import com.finsight.managermodel.BudgetManagerModel;
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
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    private final IBudgetManager budgetManager;
    private final BudgetApiMapper budgetApiMapper;

    public BudgetController(IBudgetManager budgetManager, BudgetApiMapper budgetApiMapper) {
        this.budgetManager = budgetManager;
        this.budgetApiMapper = budgetApiMapper;
    }

    @GetMapping
    public List<BudgetResponse> getAllBudgets() {
        return budgetManager.getAllBudgets().stream()
                .map(budgetApiMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BudgetResponse getBudgetById(@PathVariable Long id) {
        return budgetApiMapper.toResponse(budgetManager.getBudgetById(id));
    }

    @PostMapping
    public BudgetResponse createBudget(@Valid @RequestBody BudgetRequest request) {
        BudgetManagerModel model = budgetApiMapper.toManagerModel(request);
        return budgetApiMapper.toResponse(budgetManager.createBudget(model));
    }

    @PutMapping("/{id}")
    public BudgetResponse updateBudget(@PathVariable Long id, @Valid @RequestBody BudgetRequest request) {
        BudgetManagerModel model = budgetApiMapper.toManagerModel(request);
        return budgetApiMapper.toResponse(budgetManager.updateBudget(id, model));
    }

    @DeleteMapping("/{id}")
    public void deleteBudget(@PathVariable Long id) {
        budgetManager.deleteBudget(id);
    }
}
