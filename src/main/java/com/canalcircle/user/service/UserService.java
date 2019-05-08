package com.canalcircle.user.service;

import com.canalcircle.user.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Created by truongnguyen on 5/8/19.
 */
public interface UserService {

    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    void deleteById(Long id);

}
