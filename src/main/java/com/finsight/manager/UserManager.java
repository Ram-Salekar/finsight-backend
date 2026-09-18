package com.finsight.manager;

import com.finsight.managermodel.UserManagerModel;
import com.finsight.managertorepositorymapper.UserRepoMapper;
import com.finsight.readrepository.userreadrepo.IUserReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Unlike repositories, this DOES need a real implementation class — Spring
 * Data's auto-generation only applies to Repository/JpaRepository interfaces.
 * A manager/service is a plain Spring bean, so the usual interface+impl
 * pattern applies normally here, same as it would in .NET.
 */
@Service
public class UserManager implements IUserManager {

    private final IUserReadRepository userReadRepository;
    private final UserRepoMapper userRepoMapper;

    public UserManager(IUserReadRepository userReadRepository, UserRepoMapper userRepoMapper) {
        this.userReadRepository = userReadRepository;
        this.userRepoMapper = userRepoMapper;
    }

    @Override
    public List<UserManagerModel> getAllUsers() {
        return userReadRepository.findAll().stream()
                .map(userRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }
}
