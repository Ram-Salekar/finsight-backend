package com.finsight.manager;

import com.finsight.managermodel.AccountManagerModel;

import java.util.List;

public interface IAccountManager {
    List<AccountManagerModel> getAllAccounts();

    AccountManagerModel getAccountById(Long id);

    AccountManagerModel createAccount(AccountManagerModel model);

    AccountManagerModel updateAccount(Long id, AccountManagerModel model);

    void deleteAccount(Long id);
}
