package com.canalcircle.user.controller;

import com.canalcircle.user.model.User;
import com.canalcircle.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by truongnguyen on 5/8/19.
 */

@RunWith(SpringRunner.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mUserService;

    @Test
    public void update() throws Exception {
        User user = User.getBuilder().name("Test").description("Test").build();
        user.setId(2L);

        given(mUserService.save(user)).willReturn(user);

        this.mockMvc.perform(put("/api/v1/users/2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 2,'name': 'Test';'description': 'Test'}]"));
    }

    @Test
    public void create() throws Exception {
        User user = User.getBuilder().name("Test").description("Test").build();

        given(mUserService.save(user)).willReturn(user);

        this.mockMvc.perform(post("/api/v1/users/"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 6,'name': 'Test';'description': 'Test'}]"));
    }

}
