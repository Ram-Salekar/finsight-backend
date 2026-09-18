package com.finsight.controller;

import com.finsight.apimodel.FinancialGoalRequest;
import com.finsight.apimodel.FinancialGoalResponse;
import com.finsight.apitomanagermapper.FinancialGoalApiMapper;
import com.finsight.manager.IFinancialGoalManager;
import com.finsight.managermodel.FinancialGoalManagerModel;
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
@RequestMapping("/api/v1/financial-goals")
public class FinancialGoalController {

    private final IFinancialGoalManager financialGoalManager;
    private final FinancialGoalApiMapper financialGoalApiMapper;

    public FinancialGoalController(IFinancialGoalManager financialGoalManager, FinancialGoalApiMapper financialGoalApiMapper) {
        this.financialGoalManager = financialGoalManager;
        this.financialGoalApiMapper = financialGoalApiMapper;
    }

    @GetMapping
    public List<FinancialGoalResponse> getAllFinancialGoals() {
        return financialGoalManager.getAllFinancialGoals().stream()
                .map(financialGoalApiMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FinancialGoalResponse getFinancialGoalById(@PathVariable Long id) {
        return financialGoalApiMapper.toResponse(financialGoalManager.getFinancialGoalById(id));
    }

    @PostMapping
    public FinancialGoalResponse createFinancialGoal(@Valid @RequestBody FinancialGoalRequest request) {
        FinancialGoalManagerModel model = financialGoalApiMapper.toManagerModel(request);
        return financialGoalApiMapper.toResponse(financialGoalManager.createFinancialGoal(model));
    }

    @PutMapping("/{id}")
    public FinancialGoalResponse updateFinancialGoal(@PathVariable Long id, @Valid @RequestBody FinancialGoalRequest request) {
        FinancialGoalManagerModel model = financialGoalApiMapper.toManagerModel(request);
        return financialGoalApiMapper.toResponse(financialGoalManager.updateFinancialGoal(id, model));
    }

    @DeleteMapping("/{id}")
    public void deleteFinancialGoal(@PathVariable Long id) {
        financialGoalManager.deleteFinancialGoal(id);
    }
}
