package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.BudgetManagerModel;
import com.finsight.repositorymodel.BudgetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetRepoMapper {

    @Mapping(target = "user", ignore = true)
    BudgetEntity toEntity(BudgetManagerModel model);

    @Mapping(target = "userId", source = "user.id")
    BudgetManagerModel toManagerModel(BudgetEntity entity);
}
