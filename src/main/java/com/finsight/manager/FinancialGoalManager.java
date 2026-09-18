package com.finsight.manager;

import com.finsight.managermodel.FinancialGoalManagerModel;
import com.finsight.managertorepositorymapper.FinancialGoalRepoMapper;
import com.finsight.readrepository.financialgoalreadrepo.IFinancialGoalReadRepository;
import com.finsight.readrepository.userreadrepo.IUserReadRepository;
import com.finsight.repositorymodel.FinancialGoalEntity;
import com.finsight.repositorymodel.UserEntity;
import com.finsight.writerepository.financialgoalwriterepo.IFinancialGoalWriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinancialGoalManager implements IFinancialGoalManager {

    private final IFinancialGoalReadRepository financialGoalReadRepository;
    private final IFinancialGoalWriteRepository financialGoalWriteRepository;
    private final IUserReadRepository userReadRepository;
    private final FinancialGoalRepoMapper financialGoalRepoMapper;

    public FinancialGoalManager(IFinancialGoalReadRepository financialGoalReadRepository,
                                 IFinancialGoalWriteRepository financialGoalWriteRepository,
                                 IUserReadRepository userReadRepository,
                                 FinancialGoalRepoMapper financialGoalRepoMapper) {
        this.financialGoalReadRepository = financialGoalReadRepository;
        this.financialGoalWriteRepository = financialGoalWriteRepository;
        this.userReadRepository = userReadRepository;
        this.financialGoalRepoMapper = financialGoalRepoMapper;
    }

    @Override
    public List<FinancialGoalManagerModel> getAllFinancialGoals() {
        return financialGoalReadRepository.findAll().stream()
                .map(financialGoalRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }

    @Override
    public FinancialGoalManagerModel getFinancialGoalById(Long id) {
        FinancialGoalEntity entity = financialGoalReadRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("Financial goal not found: " + id);
        }
        return financialGoalRepoMapper.toManagerModel(entity);
    }

    @Override
    public FinancialGoalManagerModel createFinancialGoal(FinancialGoalManagerModel model) {
        UserEntity user = userReadRepository.findById(model.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + model.getUserId());
        }
        FinancialGoalEntity entity = financialGoalRepoMapper.toEntity(model);
        entity.setUser(user);
        FinancialGoalEntity saved = financialGoalWriteRepository.save(entity);
        return financialGoalRepoMapper.toManagerModel(saved);
    }

    @Override
    public FinancialGoalManagerModel updateFinancialGoal(Long id, FinancialGoalManagerModel model) {
        FinancialGoalEntity existing = financialGoalReadRepository.findById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Financial goal not found: " + id);
        }
        existing.setName(model.getName());
        existing.setTargetAmount(model.getTargetAmount());
        existing.setCurrentAmount(model.getCurrentAmount());
        existing.setDeadline(model.getDeadline());
        existing.setStatus(model.getStatus());
        if (model.getUserId() != null && !model.getUserId().equals(existing.getUser().getId())) {
            UserEntity user = userReadRepository.findById(model.getUserId());
            if (user == null) {
                throw new IllegalArgumentException("User not found: " + model.getUserId());
            }
            existing.setUser(user);
        }
        FinancialGoalEntity saved = financialGoalWriteRepository.save(existing);
        return financialGoalRepoMapper.toManagerModel(saved);
    }

    @Override
    public void deleteFinancialGoal(Long id) {
        financialGoalWriteRepository.deleteById(id);
    }
}
