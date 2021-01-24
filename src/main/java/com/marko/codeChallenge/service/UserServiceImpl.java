package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.repository.UserRepository;
import com.marko.codeChallenge.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public List<User> delete(Long id) {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        Validate.isNotNull(user, "NO_USER_FOUND_FOR_PROVIDED_DATA");
        return user;
    }
}
