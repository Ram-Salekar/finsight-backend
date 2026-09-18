package com.finsight.apitomanagermapper;

import com.finsight.apimodel.AccountRequest;
import com.finsight.apimodel.AccountResponse;
import com.finsight.managermodel.AccountManagerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountApiMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    AccountManagerModel toManagerModel(AccountRequest request);

    AccountResponse toResponse(AccountManagerModel model);
}
