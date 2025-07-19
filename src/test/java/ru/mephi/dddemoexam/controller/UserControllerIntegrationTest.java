package ru.mephi.dddemoexam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.mephi.dddemoexam.dto.UserRequest;
import ru.mephi.dddemoexam.model.Country;
import ru.mephi.dddemoexam.model.User;
import ru.mephi.dddemoexam.repository.UserRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testGetAllUsers_returnsListOfUsers() throws Exception {

        User user1 = new User(null, "Alice", 25, Country.RUSSIA);
        User user2 = new User(null, "Bob", 30, Country.AMERICA);
        userRepository.saveAll(List.of(user1, user2));

        mockMvc.perform(get("/user-api/v1/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].firstName", is("Alice")))
                .andExpect(jsonPath("$[1].firstName", is("Bob")));
    }

    @Test
    void testCreateUser_returnsCreatedUser() throws Exception {

        UserRequest request = new UserRequest("Charlie", 22, Country.FRANCE);

        mockMvc.perform(post("/user-api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is("Charlie")))
                .andExpect(jsonPath("$.country", is("FRANCE")));


        assertEquals(1, userRepository.count());
    }

    @Test
    void testGetUsersByMinAge_returnsFilteredAndSortedUsers() throws Exception {

        userRepository.saveAll(List.of(
                new User(null, "Elena", 22, Country.RUSSIA),
                new User(null, "David", 17, Country.BRAZIL),
                new User(null, "Charlie", 30, Country.FRANCE)
        ));

        mockMvc.perform(get("/user-api/v1/additional-info")
                        .param("age", "18"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].firstName", is("Charlie")))
                .andExpect(jsonPath("$[1].firstName", is("Elena")));
    }
}
