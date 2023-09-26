package project.music.spring.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.music.spring.controller.UserController;
import project.music.spring.mapper.UserMapper;
import project.music.spring.model.dto.UserDTO;
import project.music.spring.model.entity.User;
import project.music.spring.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Andrea", "andrea@gmail.com", "password", "febrero 2"));
        userList.add(new User("Laura", "laura@gmail.com", "password", "febrero 2"));
        when(userService.getUsers()).thenReturn(userList);



    }

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].name").value("Andrea"))
                .andExpect(jsonPath("$[0].email").value("andrea@gmail.com"))
                .andExpect(jsonPath("$[0].password").value("password"))
                .andExpect(jsonPath("$[0].birthdate").value("febrero 2"))
                .andExpect(jsonPath("$[1].name").value("Laura"))
                .andExpect(jsonPath("$[1].email").value("laura@gmail.com"))
                .andExpect(jsonPath("$[1].password").value("password"))
                .andExpect(jsonPath("$[1].birthdate").value("febrero 2"));
        verify(userService, times(1)).getUsers();
    }



}
