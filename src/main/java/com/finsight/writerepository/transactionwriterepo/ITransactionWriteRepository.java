package com.finsight.writerepository.transactionwriterepo;

import com.finsight.repositorymodel.TransactionEntity;
import org.springframework.data.repository.Repository;

public interface ITransactionWriteRepository extends Repository<TransactionEntity, Long> {
    TransactionEntity save(TransactionEntity entity);

    void deleteById(Long id);
}
