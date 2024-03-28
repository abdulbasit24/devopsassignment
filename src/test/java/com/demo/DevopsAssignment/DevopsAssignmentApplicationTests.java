package com.demo.DevopsAssignment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import java.awt.PageAttributes.MediaType;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.demo.DevopsAssignment.Controller.UserController;
import com.demo.DevopsAssignment.Model.User;
import com.demo.DevopsAssignment.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class DevopsAssignmentApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() throws Exception {
        User newUser = new User(3, "Aman", "aman@yahoo.com");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(newUser));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "abdul basit", "abdul@basit.com"));
        userList.add(new User(2, "fahad", "fahad@gmail.com"));

        when(userRepository.findAll()).thenReturn(userList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(userList));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1, "abdul basit", "abdul@basit.com");
        when(userRepository.findById(user.getEmpid())).thenReturn(Optional.of(user));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", user.getEmpid()))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(user));
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        // Mocking userRepository to return Optional.empty() when findById is called with ID 100
        when(userRepository.findById(100)).thenReturn(Optional.empty());

        // Performing GET request to /user/{id} with ID 100
        mockMvc.perform(get("/user/{id}", 100))
               // Expecting HTTP status code 404 (Not Found)
               .andExpect(status().isNotFound());
    }
}
