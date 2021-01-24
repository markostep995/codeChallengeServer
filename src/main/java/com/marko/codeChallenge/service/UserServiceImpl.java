package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.User;
import com.marko.codeChallenge.repository.UserRepository;
import com.marko.codeChallenge.util.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User create(User user) {
        Validate.isNotNull(user, "USER_FOR_CREATE_IS_NULL");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setDeleted(false);
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public User update(User user) {
        Validate.isEntityOrIdNull(user, "USER_FOR_UPDATE_IS_NULL");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public List<User> findAllNotDeleted() {
        return userRepository.findByIsDeletedOrderByIdAsc(false);
    }

    @Override
    public List<User> delete(Long id) {
        User userForDelete = userRepository.findById(id).get();
        userForDelete.setDeleted(true);
        userForDelete.setActive(false);
        userRepository.save(userForDelete);
        return findAllNotDeleted();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        Validate.isNotNull(user, "NO_USER_FOUND_FOR_PROVIDED_DATA");
        return user;
    }

}
