package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private final Map<Long, User> userMap;

    public UserRepository() {
        this.userMap = new HashMap<>();
    }

    public Optional<User> getById(int id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public void add(User user) throws RepositoryException {
        if (userMap.containsKey(user.getId())) {
            throw new RepositoryException("User with ID " + user.getId() + " already exists in the repository");
        }
        userMap.put(user.getId(), user);
    }

    public void update(User user) throws RepositoryException {
        if (!userMap.containsKey(user.getId())) {
            throw new RepositoryException("User with ID " + user.getId() + " already exists in the repository");
        }
        userMap.put(user.getId(), user);
    }

    public void delete(Long id) throws RepositoryException {
        if (!userMap.containsKey(id)) {
            throw new RepositoryException("User with ID " + id + " does not exists in the repository");
        }
        userMap.remove(id);
    }

    public List<User> findAll() {
        return List.copyOf(userMap.values());
    }
}
