package com.finsight.readrepository.budgetreadrepo;

import com.finsight.repositorymodel.BudgetEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IBudgetReadRepository extends Repository<BudgetEntity, Long> {
    List<BudgetEntity> findAll();

    BudgetEntity findById(Long id);

    List<BudgetEntity> findAllByUserId(Long userId);
}
