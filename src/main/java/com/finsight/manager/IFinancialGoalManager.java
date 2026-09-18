package com.finsight.manager;

import com.finsight.managermodel.FinancialGoalManagerModel;

import java.util.List;

public interface IFinancialGoalManager {
    List<FinancialGoalManagerModel> getAllFinancialGoals();

    FinancialGoalManagerModel getFinancialGoalById(Long id);

    FinancialGoalManagerModel createFinancialGoal(FinancialGoalManagerModel model);

    FinancialGoalManagerModel updateFinancialGoal(Long id, FinancialGoalManagerModel model);

    void deleteFinancialGoal(Long id);
}
