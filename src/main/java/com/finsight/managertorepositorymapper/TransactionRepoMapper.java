package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.TransactionManagerModel;
import com.finsight.repositorymodel.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionRepoMapper {

    /**
     * "account" is deliberately not mapped here — TransactionManager resolves
     * the AccountEntity via IAccountReadRepository and sets it explicitly.
     */
    @Mapping(target = "account", ignore = true)
    TransactionEntity toEntity(TransactionManagerModel model);

    @Mapping(target = "accountId", source = "account.id")
    TransactionManagerModel toManagerModel(TransactionEntity entity);
}
