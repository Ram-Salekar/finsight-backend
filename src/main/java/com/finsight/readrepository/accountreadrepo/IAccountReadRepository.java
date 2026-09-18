package com.finsight.readrepository.accountreadrepo;

import com.finsight.repositorymodel.AccountEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface IAccountReadRepository extends Repository<AccountEntity, Long> {
    List<AccountEntity> findAll();

    AccountEntity findById(Long id);

    List<AccountEntity> findAllByUserId(Long userId);
}
