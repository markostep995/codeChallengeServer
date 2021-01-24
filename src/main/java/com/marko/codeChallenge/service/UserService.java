package com.marko.codeChallenge.service;

import com.marko.codeChallenge.model.User;

public interface UserService extends BasicService<User>{
    User findByUsernameAndPassword(String username, String password);
}
