package com.finsight.readrepository.transactionreadrepo;

import com.finsight.repositorymodel.TransactionEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ITransactionReadRepository extends Repository<TransactionEntity, Long> {
    List<TransactionEntity> findAll();

    TransactionEntity findById(Long id);

    List<TransactionEntity> findAllByAccountId(Long accountId);
}
