package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.AccountManagerModel;
import com.finsight.repositorymodel.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountRepoMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    AccountEntity toEntity(AccountManagerModel model);

    @Mapping(target = "userId", source = "user.id")
    AccountManagerModel toManagerModel(AccountEntity entity);
}
