package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.UserManagerModel;
import com.finsight.repositorymodel.UserEntity;
import org.mapstruct.Mapper;

/**
 * Maps between the manager layer (domain model) and the repo layer (JPA
 * entity). Note: createdAt/createdBy/lastModifiedAt/lastModifiedBy have no
 * setters on UserEntity (inherited from AuditableEntity) — they're populated
 * automatically by Spring Data JPA auditing, not by this mapper. MapStruct
 * simply skips target properties it has no setter for.
 */
@Mapper(componentModel = "spring")
public interface UserRepoMapper {

    UserEntity toEntity(UserManagerModel model);

    // No ignores needed here: UserEntity's audit getters and UserManagerModel's
    // audit setters both exist, so MapStruct auto-maps createdAt/createdBy/
    // lastModifiedAt/lastModifiedBy/deleted by matching property names.
    UserManagerModel toManagerModel(UserEntity entity);
}
