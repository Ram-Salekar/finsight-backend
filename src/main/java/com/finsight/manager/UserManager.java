package com.finsight.manager;

import com.finsight.managermodel.UserManagerModel;
import com.finsight.managertorepositorymapper.UserRepoMapper;
import com.finsight.readrepository.userreadrepo.IUserReadRepository;
import com.finsight.repositorymodel.UserEntity;
import com.finsight.writerepository.userwriterepo.IUserWriteRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final IUserWriteRepository userWriteRepository;
    private final UserRepoMapper userRepoMapper;
    private final PasswordEncoder passwordEncoder;

    public UserManager(IUserReadRepository userReadRepository,
                        IUserWriteRepository userWriteRepository,
                        UserRepoMapper userRepoMapper,
                        PasswordEncoder passwordEncoder) {
        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
        this.userRepoMapper = userRepoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserManagerModel> getAllUsers() {
        return userReadRepository.findAll().stream()
                .map(userRepoMapper::toManagerModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserManagerModel getUserById(Long id) {
        UserEntity entity = userReadRepository.findById(id);
        if (entity == null) {
            throw new IllegalArgumentException("User not found: " + id);
        }
        return userRepoMapper.toManagerModel(entity);
    }

    @Override
    public UserManagerModel registerUser(UserManagerModel model, String rawPassword) {
        // Hashing happens here, once, at the manager layer — never in the
        // entity, never in a mapper. See earlier discussion: hashing inside
        // an entity setter would re-hash on every load from the DB.
        String hashed = passwordEncoder.encode(rawPassword);
        model.setPasswordHash(hashed);

        UserEntity entity = userRepoMapper.toEntity(model);
        UserEntity saved = userWriteRepository.save(entity);
        return userRepoMapper.toManagerModel(saved);
    }

    @Override
    public void deleteUser(Long id) {
        userWriteRepository.deleteById(id);
    }
}
