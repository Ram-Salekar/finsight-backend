package com.finsight.manager;

import com.finsight.managermodel.TransactionManagerModel;
import com.finsight.managertorepositorymapper.TransactionRepoMapper;
import com.finsight.readrepository.accountreadrepo.IAccountReadRepository;
import com.finsight.readrepository.transactionreadrepo.ITransactionReadRepository;
import com.finsight.repositorymodel.AccountEntity;
import com.finsight.repositorymodel.TransactionEntity;
import com.finsight.writerepository.transactionwriterepo.ITransactionWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Explicit bean name required: the default (class name, first letter
 * lowercased) would be "transactionManager", which collides with Spring's
 * own internal PlatformTransactionManager bean — the one that powers
 * @Transactional / JPA transaction handling. That's a reserved name, not a
 * coincidence to design around; every other manager in this app is fine
 * with the default name precisely because none of them happen to match a
 * Spring-internal bean name.
 */
@Service("financialTransactionManager")
public class TransactionManager implements ITransactionManager {

    private final ITransactionReadRepository transactionReadRepository;
    private final ITransactionWriteRepository transactionWriteRepository;
    private final IAccountReadRepository accountReadRepository;
    private final TransactionRepoMapper transactionRepoMapper;

    public TransactionManager(ITransactionReadRepository transactionReadRepository,
                               ITransactionWriteRepository transactionWriteRepository,
                               IAccountReadRepository accountReadRepository,
                               TransactionRepoMapper transactionRepoMapper) {
        this.transactionReadRepository = transactionReadRepository;
        this.transactionWriteRepository = transactionWriteRepository;
        this.accountReadRepository = accountReadRepository;
        this.transactionRepoMapper = transactionRepoMapper;
    }

    @Override
    public List<TransactionManagerModel> getAllTransactions() {
        return transactionReadRepository.findAll().stream()
                .map(transactionRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionManagerModel getTransactionById(Long id) {
        TransactionEntity entity = transactionReadRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Transaction not found: " + id);
        }
        return transactionRepoMapper.toManagerModel(entity);
    }

    @Override
    public TransactionManagerModel createTransaction(TransactionManagerModel model) {
        AccountEntity account = accountReadRepository.findById(model.getAccountId());
        if (account == null) {
            throw new IllegalArgumentException("Account not found: " + model.getAccountId());
        }
        TransactionEntity entity = transactionRepoMapper.toEntity(model);
        entity.setAccount(account);
        TransactionEntity saved = transactionWriteRepository.save(entity);
        return transactionRepoMapper.toManagerModel(saved);
    }

    @Override
    public TransactionManagerModel updateTransaction(Long id, TransactionManagerModel model) {
        TransactionEntity existing = transactionReadRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Transaction not found: " + id);
        }
        existing.setType(model.getType());
        existing.setAmount(model.getAmount());
        existing.setCategory(model.getCategory());
        existing.setTransactionDate(model.getTransactionDate());
        existing.setDescription(model.getDescription());
        if (model.getAccountId() != null && !model.getAccountId().equals(existing.getAccount().getId())) {
            AccountEntity account = accountReadRepository.findById(model.getAccountId());
            if (account == null) {
                throw new IllegalArgumentException("Account not found: " + model.getAccountId());
            }
            existing.setAccount(account);
        }
        TransactionEntity saved = transactionWriteRepository.save(existing);
        return transactionRepoMapper.toManagerModel(saved);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionWriteRepository.deleteById(id);
    }
}
