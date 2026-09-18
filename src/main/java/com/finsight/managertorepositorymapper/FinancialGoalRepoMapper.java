package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.FinancialGoalManagerModel;
import com.finsight.repositorymodel.FinancialGoalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialGoalRepoMapper {

    @Mapping(target = "user", ignore = true)
    FinancialGoalEntity toEntity(FinancialGoalManagerModel model);

    @Mapping(target = "userId", source = "user.id")
    FinancialGoalManagerModel toManagerModel(FinancialGoalEntity entity);
}
