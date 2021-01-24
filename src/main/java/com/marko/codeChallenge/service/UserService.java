package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.User;

import java.util.List;

public interface UserService extends BasicService<User>{
    User findByUsernameAndPassword(String username, String password);
    List<User> findAllNotDeleted();
}
