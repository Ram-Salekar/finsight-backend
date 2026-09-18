package com.finsight.writerepository.userwriterepo;

import com.finsight.repositorymodel.UserEntity;
import org.springframework.data.repository.Repository;

public interface IUserWriteRepository extends Repository<UserEntity, Long> {
    UserEntity save(UserEntity entity);

    void deleteById(Long id);
}
