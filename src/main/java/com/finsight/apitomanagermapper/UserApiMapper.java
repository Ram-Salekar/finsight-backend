package com.finsight.apitomanagermapper;

import com.finsight.apimodel.UserRequest;
import com.finsight.apimodel.UserResponse;
import com.finsight.managermodel.UserManagerModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Maps between the controller layer (API DTOs) and the manager layer
 * (domain model). MapStruct generates the actual implementation at compile
 * time — see target/generated-sources/annotations after building.
 */
@Mapper(componentModel = "spring")
public interface UserApiMapper {

    /**
     * "password" (raw) intentionally does NOT map to "passwordHash" here —
     * hashing is a manager-layer concern (via PasswordEncoder), not something
     * a mapper should do. The manager sets passwordHash itself after encoding.
     */
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedAt", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    UserManagerModel toManagerModel(UserRequest request);

    UserResponse toResponse(UserManagerModel model);
}
