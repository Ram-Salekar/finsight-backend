package com.finsight.manager;

import com.finsight.managermodel.AccountManagerModel;
import com.finsight.managertorepositorymapper.AccountRepoMapper;
import com.finsight.readrepository.accountreadrepo.IAccountReadRepository;
import com.finsight.readrepository.userreadrepo.IUserReadRepository;
import com.finsight.repositorymodel.AccountEntity;
import com.finsight.repositorymodel.UserEntity;
import com.finsight.writerepository.accountwriterepo.IAccountWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountManager implements IAccountManager {

    private final IAccountReadRepository accountReadRepository;
    private final IAccountWriteRepository accountWriteRepository;
    private final IUserReadRepository userReadRepository;
    private final AccountRepoMapper accountRepoMapper;

    public AccountManager(IAccountReadRepository accountReadRepository,
                           IAccountWriteRepository accountWriteRepository,
                           IUserReadRepository userReadRepository,
                           AccountRepoMapper accountRepoMapper) {
        this.accountReadRepository = accountReadRepository;
        this.accountWriteRepository = accountWriteRepository;
        this.userReadRepository = userReadRepository;
        this.accountRepoMapper = accountRepoMapper;
    }

    @Override
    public List<AccountManagerModel> getAllAccounts() {
        return accountReadRepository.findAll().stream()
                .map(accountRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }

    @Override
    public AccountManagerModel getAccountById(Long id) {
        AccountEntity entity = accountReadRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Account not found: " + id);
        }
        return accountRepoMapper.toManagerModel(entity);
    }

    @Override
    public AccountManagerModel createAccount(AccountManagerModel model) {
        UserEntity user = userReadRepository.findById(model.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + model.getUserId());
        }
        AccountEntity entity = accountRepoMapper.toEntity(model);
        entity.setUser(user);
        AccountEntity saved = accountWriteRepository.save(entity);
        return accountRepoMapper.toManagerModel(saved);
    }

    @Override
    public AccountManagerModel updateAccount(Long id, AccountManagerModel model) {
        AccountEntity existing = accountReadRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Account not found: " + id);
        }
        existing.setBankName(model.getBankName());
        existing.setCurrency(model.getCurrency());
        existing.setType(model.getType());
        existing.setBalance(model.getBalance());
        if (model.getUserId() != null && !model.getUserId().equals(existing.getUser().getId())) {
            UserEntity user = userReadRepository.findById(model.getUserId());
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + model.getUserId());
            }
            existing.setUser(user);
        }
        AccountEntity saved = accountWriteRepository.save(existing);
        return accountRepoMapper.toManagerModel(saved);
    }

    @Override
    public void deleteAccount(Long id) {
        accountWriteRepository.deleteById(id);
    }
}
