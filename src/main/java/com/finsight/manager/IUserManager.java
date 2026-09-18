package com.finsight.manager;

import com.finsight.managermodel.UserManagerModel;

import java.util.List;

public interface IUserManager {

    List<UserManagerModel> getAllUsers();

    UserManagerModel getUserById(Long id);

    /**
     * rawPassword is passed separately (not on UserManagerModel) so it's
     * obvious at the call site that this is the one raw, unhashed value in
     * the whole flow — it gets hashed inside this method, never stored as-is.
     */
    UserManagerModel registerUser(UserManagerModel model, String rawPassword);

    void deleteUser(Long id);
}
