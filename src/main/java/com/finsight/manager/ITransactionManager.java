package com.finsight.manager;

import com.finsight.managermodel.TransactionManagerModel;

import java.util.List;

public interface ITransactionManager {
    List<TransactionManagerModel> getAllTransactions();

    TransactionManagerModel getTransactionById(Long id);

    TransactionManagerModel createTransaction(TransactionManagerModel model);

    TransactionManagerModel updateTransaction(Long id, TransactionManagerModel model);

    void deleteTransaction(Long id);
}
