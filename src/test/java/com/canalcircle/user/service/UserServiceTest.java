package com.canalcircle.user.service;

import com.canalcircle.user.model.User;
import com.canalcircle.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by truongnguyen on 5/8/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository mUserRepository;

    @InjectMocks
    private UserServiceIml mUserServiceIml;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUser(){
        List<User> users = new ArrayList<User>();
        users.add(User.getBuilder().name("Truong").description("Des").build());
        when(mUserRepository.findAll()).thenReturn(users);

        List<User> result = mUserServiceIml.findAll();
        assertEquals(1, result.size());
    }

    @Test
    public void saveUser(){
        User user = User.getBuilder().name("Truong").description("Des").build();
        when(mUserRepository.save(user)).thenReturn(user);
        User result = mUserServiceIml.save(user);
        assertEquals("Truong", result.getName());
        assertEquals("Des", result.getDescription());
    }
}
