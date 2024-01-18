package org.marx.PP_3_1_2_CROD.SPRINBOOT.dao;


import org.marx.PP_3_1_2_CROD.SPRINBOOT.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager env;

    @Override
    @Transactional
    public void createUser(User user) {
        env.persist(user);
    }

    @Override
    public Optional<User> getUser(long userId) {
        return Optional.ofNullable(env.find(User.class, userId));
    }

    @Override
    public List<User> getAllUsers() {
        return env.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        env.merge(user);
    }

    @Override
    @Transactional
    public void deleteUser(long userId) {
        Optional<User> user = getUser(userId);
        env.remove(user.get());
    }
}
