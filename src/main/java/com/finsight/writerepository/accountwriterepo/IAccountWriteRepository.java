package com.finsight.writerepository.accountwriterepo;

import com.finsight.repositorymodel.AccountEntity;
import org.springframework.data.repository.Repository;

public interface IAccountWriteRepository extends Repository<AccountEntity, Long> {
    AccountEntity save(AccountEntity entity);

    void deleteById(Long id);
}
