package com.finsight.apitomanagermapper;

import com.finsight.apimodel.BudgetRequest;
import com.finsight.apimodel.BudgetResponse;
import com.finsight.managermodel.BudgetManagerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    BudgetManagerModel toManagerModel(BudgetRequest request);

    BudgetResponse toResponse(BudgetManagerModel model);
}
