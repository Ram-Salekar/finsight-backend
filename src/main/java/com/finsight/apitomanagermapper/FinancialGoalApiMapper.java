package com.finsight.apitomanagermapper;

import com.finsight.apimodel.FinancialGoalRequest;
import com.finsight.apimodel.FinancialGoalResponse;
import com.finsight.managermodel.FinancialGoalManagerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FinancialGoalApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    FinancialGoalManagerModel toManagerModel(FinancialGoalRequest request);

    FinancialGoalResponse toResponse(FinancialGoalManagerModel model);
}
