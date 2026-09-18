package com.finsight.writerepository.budgetwriterepo;

import com.finsight.repositorymodel.BudgetEntity;
import org.springframework.data.repository.Repository;

public interface IBudgetWriteRepository extends Repository<BudgetEntity, Long> {
    BudgetEntity save(BudgetEntity entity);

    void deleteById(Long id);
}
