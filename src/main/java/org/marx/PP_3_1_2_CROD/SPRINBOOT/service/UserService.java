package org.marx.PP_3_1_2_CROD.SPRINBOOT.service;

import org.marx.PP_3_1_2_CROD.SPRINBOOT.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void createUser(User user);

    Optional<User> getUser(long userId);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(long userId);
}