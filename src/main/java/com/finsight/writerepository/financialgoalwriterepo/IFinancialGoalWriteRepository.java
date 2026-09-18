package com.finsight.writerepository.financialgoalwriterepo;

import com.finsight.repositorymodel.FinancialGoalEntity;
import org.springframework.data.repository.Repository;

public interface IFinancialGoalWriteRepository extends Repository<FinancialGoalEntity, Long> {
    FinancialGoalEntity save(FinancialGoalEntity entity);

    void deleteById(Long id);
}
