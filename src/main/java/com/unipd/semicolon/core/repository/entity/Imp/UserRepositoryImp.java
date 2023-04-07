package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.Role;
import com.unipd.semicolon.core.entity.User;
import com.unipd.semicolon.core.repository.entity.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImp extends CustomRepository implements UserRepository {

    @Transactional
    @Override
    public User save(User user) {
        return save(User.class, user);
    }

    @Override
    public User findUserById(Long id) {
        return findById(User.class, id);
    }

    @Override
    public List<User> getAll() {
        return listQueryWrapper(entityManager.createQuery(
                "select g from User g order by g.id desc ",
                User.class));
    }

    @Override
    public List<User> findAllByLastName(String lastName) {
        return listQueryWrapper(entityManager.createQuery(
                "SELECT g FROM User g WHERE g.lastName = :lastName ORDER BY g.id DESC",
                User.class).setParameter("lastName", lastName));
    }

    @Override
    public List<User> findByRoles(Role role) {
        String roleName = role.getRole();
        return listQueryWrapper(
                entityManager.createQuery("SELECT u FROM User u WHERE u.role.role =: roleName", User.class));
    }
}




