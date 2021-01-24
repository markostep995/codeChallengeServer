package com.marko.codeChallenge.service;


import java.util.List;

public interface BasicService<T> {
    T create(T user);
    T update(T user);
    T findById(Long id);
    List<T> findAll();
    List<T> delete(Long id);
}
