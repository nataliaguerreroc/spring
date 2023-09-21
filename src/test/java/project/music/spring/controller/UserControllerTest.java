//package project.music.spring.controller;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import project.music.spring.controller.UserController;
//import project.music.spring.mapper.UserMapper;
//import project.music.spring.model.dto.UserDTO;
//import project.music.spring.model.entity.User;
//import project.music.spring.service.UserService;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private UserMapper userMapper;
//
//    @BeforeEach
//    public void setUp() {
//        userService = mock(UserService.class);
//
//        userMapper = mock(UserMapper.class);
//
//        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService, userMapper)).build();
//    }
//
//    @Test
//    public void createUser() throws Exception{
//        User newUser = new User("Andrea", "andrea@gmail.com", "password", "febrero 2");
//
//        when(userService.add(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(newUser);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String userContent = objectMapper.writeValueAsString(newUser);
//
//        mockMvc.perform(post("/users")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(userContent)
//                )
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.name").value("Andrea"))
//                .andExpect(jsonPath("$.email").value("andrea@gmail.com"))
//                .andExpect(jsonPath("$.password").value("password"))
//                .andExpect(jsonPath("$.birthdate").value("febrero 2"));
//    }
//
////    @Test
////    public void getUser() throws Exception{
////        List<User> userList = new ArrayList<>();
////        userList.add(new User("Andrea", "andrea@gmail.com", "password", "febrero 2"));
////        when(userService.getUsers()).thenReturn(userList);
////
////        mockMvc.perform(get("/users"))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$", hasSize(1)))
////                .andExpect(jsonPath("$[0].name").value("Andrea"))
////                .andExpect(jsonPath("$[0].email").value("andrea@gmail.com"))
////                .andExpect(jsonPath("$[0].password").value("password"))
////                .andExpect(jsonPath("$[0].birthdate").value("febrero 2"));
////    }
//
//    @Test
//    public void getUser() throws Exception{
//        List<UserDTO> userDTOList = new ArrayList<>();
//        userDTOList.add(new UserDTO("Andrea", "andrea@gmail.com", "password", "febrero 2"));
//
//        // Configura el comportamiento del servicio
//        when(userService.getUsers()).thenReturn(userMapper.entityListDTOToList(userDTOList));
//
//
//        mockMvc.perform(get("/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name").value("Andrea"))
//                .andExpect(jsonPath("$[0].email").value("andrea@gmail.com"))
//                .andExpect(jsonPath("$[0].password").value("password"))
//                .andExpect(jsonPath("$[0].birthdate").value("febrero 2"));
//    }
//
//    @Test
//    public void deleteUser() throws Exception{
//        UUID userID = UUID.randomUUID();
//        mockMvc.perform(delete("/users/{id}", userID))
//                .andExpect(status().isOk());
//
//        verify(userService, times(1)).deleteById(userID);
//    }
//
//    @Test
//    public void updateUser() throws Exception{
//        UUID userID = UUID.randomUUID();
//        when(userService.updateById(any(), any(), any(), any(), eq(userID)))
//                .thenReturn(new User("Camilo", "camilo@gmail.com", "password", "marzo 2"));
//
//        Map<String, String> userTemp = new HashMap<>();
//        userTemp.put("name", "Camilo");
//        userTemp.put("email", "camilo@gmail.com");
//        userTemp.put("password", "password");
//        userTemp.put("birthdate", "marzo 2");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String userContent = objectMapper.writeValueAsString(userTemp);
//
//        mockMvc.perform(put("/users/{id}", 1L)
//               .contentType(MediaType.APPLICATION_JSON)
//               .content(userContent)
//        )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Camilo"))
//                .andExpect(jsonPath("$.email").value("camilo@gmail.com"))
//                .andExpect(jsonPath("$.password").value("password"))
//                .andExpect(jsonPath("$.birthdate").value("marzo 2"));
//
//    }
//
//
//}
