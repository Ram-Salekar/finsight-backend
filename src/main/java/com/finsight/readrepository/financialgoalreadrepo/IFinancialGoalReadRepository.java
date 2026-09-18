package com.finsight.readrepository.financialgoalreadrepo;

import com.finsight.repositorymodel.FinancialGoalEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IFinancialGoalReadRepository extends Repository<FinancialGoalEntity, Long> {
    List<FinancialGoalEntity> findAll();

    FinancialGoalEntity findById(Long id);

    List<FinancialGoalEntity> findAllByUserId(Long userId);
}
