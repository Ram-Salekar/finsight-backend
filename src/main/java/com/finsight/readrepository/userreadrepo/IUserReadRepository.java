package com.finsight.readrepository.userreadrepo;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.finsight.repositorymodel.UserEntity;

public interface IUserReadRepository extends Repository<UserEntity, Long> {
    List<UserEntity> findAll();

    UserEntity findById(Long id);
}
