package com.marko.codeChallenge.repository;

import com.marko.codeChallenge.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User getById(Long id);

    User findByUsernameAndPassword(String username, String password);
}
