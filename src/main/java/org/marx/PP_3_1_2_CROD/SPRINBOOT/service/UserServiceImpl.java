package org.marx.PP_3_1_2_CROD.SPRINBOOT.service;


import org.marx.PP_3_1_2_CROD.SPRINBOOT.dao.UserDao;
import org.marx.PP_3_1_2_CROD.SPRINBOOT.model.User;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public Optional<User> getUser(long userId) {
        return userDao.getUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void deleteUser(long userId) {
        try {
            userDao.deleteUser(userId);
        } catch (InvalidDataAccessApiUsageException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}