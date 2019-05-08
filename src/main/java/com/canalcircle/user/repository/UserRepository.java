package com.canalcircle.user.repository;

import com.canalcircle.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by truongnguyen on 5/8/19.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
