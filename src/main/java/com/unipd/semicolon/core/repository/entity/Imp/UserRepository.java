package com.unipd.semicolon.core.repository.entity.Imp;

import com.unipd.semicolon.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(Long id);

    User save(User user);

    void delete(User user);

    List<User> findAll();
}
