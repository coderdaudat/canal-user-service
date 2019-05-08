package com.canalcircle.user.controller;

import com.canalcircle.user.model.MessageResponse;
import com.canalcircle.user.model.User;
import com.canalcircle.user.service.UserService;
import com.canalcircle.user.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by truongnguyen on 5/8/19.
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger sLogger = LoggerFactory.getLogger(UserController.class);

    private final UserService mUserService;

    @Autowired
    UserController(UserService userService) {
        this.mUserService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(mUserService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (TextUtils.isEmpty(user.getName())) {
            return new ResponseEntity(new MessageResponse("Name must not be empty"), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(mUserService.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOneUserById(@PathVariable Long id) {
        if (id == null || (id != null && id == 0)) {
            sLogger.error("id not ");
            return new ResponseEntity(new MessageResponse("id must not null or zero "), HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = mUserService.findById(id);
        if (!user.isPresent()) {
            sLogger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!mUserService.findById(id).isPresent()) {
            sLogger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(mUserService.save(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (!mUserService.findById(id).isPresent()) {
            sLogger.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        mUserService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
