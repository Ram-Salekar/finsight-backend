package com.finsight.apitomanagermapper;

import com.finsight.apimodel.TransactionRequest;
import com.finsight.apimodel.TransactionResponse;
import com.finsight.managermodel.TransactionManagerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionApiMapper {

    @Mapping(target = "id", ignore = true)
    TransactionManagerModel toManagerModel(TransactionRequest request);

    TransactionResponse toResponse(TransactionManagerModel model);
}
