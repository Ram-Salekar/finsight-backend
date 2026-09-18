package com.finsight.manager;

import com.finsight.managermodel.BudgetManagerModel;

import java.util.List;

public interface IBudgetManager {
    List<BudgetManagerModel> getAllBudgets();

    BudgetManagerModel getBudgetById(Long id);

    BudgetManagerModel createBudget(BudgetManagerModel model);

    BudgetManagerModel updateBudget(Long id, BudgetManagerModel model);

    void deleteBudget(Long id);
}
