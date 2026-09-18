package com.finsight.managertorepositorymapper;

import com.finsight.managermodel.UserManagerModel;
import com.finsight.repositorymodel.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRepoMapper {

    UserEntity toEntity(UserManagerModel model);

    // No ignores needed here: UserEntity's audit getters and UserManagerModel's
    // audit setters both exist, so MapStruct auto-maps createdAt/createdBy/
    // lastModifiedAt/lastModifiedBy/deleted by matching property names.
    UserManagerModel toManagerModel(UserEntity entity);
}
