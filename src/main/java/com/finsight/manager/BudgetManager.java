package com.finsight.manager;

import com.finsight.managermodel.BudgetManagerModel;
import com.finsight.managertorepositorymapper.BudgetRepoMapper;
import com.finsight.readrepository.budgetreadrepo.IBudgetReadRepository;
import com.finsight.readrepository.userreadrepo.IUserReadRepository;
import com.finsight.repositorymodel.BudgetEntity;
import com.finsight.repositorymodel.UserEntity;
import com.finsight.writerepository.budgetwriterepo.IBudgetWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BudgetManager implements IBudgetManager {

    private final IBudgetReadRepository budgetReadRepository;
    private final IBudgetWriteRepository budgetWriteRepository;
    private final IUserReadRepository userReadRepository;
    private final BudgetRepoMapper budgetRepoMapper;

    public BudgetManager(IBudgetReadRepository budgetReadRepository,
                          IBudgetWriteRepository budgetWriteRepository,
                          IUserReadRepository userReadRepository,
                          BudgetRepoMapper budgetRepoMapper) {
        this.budgetReadRepository = budgetReadRepository;
        this.budgetWriteRepository = budgetWriteRepository;
        this.userReadRepository = userReadRepository;
        this.budgetRepoMapper = budgetRepoMapper;
    }

    @Override
    public List<BudgetManagerModel> getAllBudgets() {
        return budgetReadRepository.findAll().stream()
                .map(budgetRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }

    @Override
    public BudgetManagerModel getBudgetById(Long id) {
        BudgetEntity entity = budgetReadRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Budget not found: " + id);
        }
        return budgetRepoMapper.toManagerModel(entity);
    }

    @Override
    public BudgetManagerModel createBudget(BudgetManagerModel model) {
        UserEntity user = userReadRepository.findById(model.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + model.getUserId());
        }
        BudgetEntity entity = budgetRepoMapper.toEntity(model);
        entity.setUser(user);
        BudgetEntity saved = budgetWriteRepository.save(entity);
        return budgetRepoMapper.toManagerModel(saved);
    }

    @Override
    public BudgetManagerModel updateBudget(Long id, BudgetManagerModel model) {
        BudgetEntity existing = budgetReadRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Budget not found: " + id);
        }
        existing.setAmount(model.getAmount());
        existing.setMonth(model.getMonth());
        existing.setYear(model.getYear());
        if (model.getUserId() != null && !model.getUserId().equals(existing.getUser().getId())) {
            UserEntity user = userReadRepository.findById(model.getUserId());
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + model.getUserId());
            }
            existing.setUser(user);
        }
        BudgetEntity saved = budgetWriteRepository.save(existing);
        return budgetRepoMapper.toManagerModel(saved);
    }

    @Override
    public void deleteBudget(Long id) {
        budgetWriteRepository.deleteById(id);
    }
}
