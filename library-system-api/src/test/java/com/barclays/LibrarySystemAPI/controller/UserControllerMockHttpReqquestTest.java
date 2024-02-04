package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Sql("classpath:test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
class UserControllerMockHttpReqquestTest {

    @Autowired
    MockMvc mockMvc;
    ObjectMapper mapper =new ObjectMapper();
    ResultActions resultActions;
    @BeforeEach
    void setUp() {
    }

    @Test
    void findAllUsers() throws Exception {
        int expectedUserListLength = 3;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User[] actualUserList = mapper.readValue(contentAsString, User[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedUserListLength, actualUserList.length),
                () -> assertEquals("Tolu Adetomiwa", actualUserList[0].getName()));

    }

    @Test
    void findUserById() throws Exception {
        String  expectedUserName = "Tolu Adetomiwa";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User actualUser = mapper.readValue(contentAsString, User.class);

        assertAll("Testing from a test-data.sql file to find users by ID ",
                () -> assertEquals(expectedUserName, actualUser.getName()));



    }

    @Test
    void createUser() {
    }

    @Test
    void getUser() throws Exception {
        int expectedLength = 1;
        String  expectedUserEmail = "damibankole@gmail.com";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/search?name=Dami")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User[] actualUser = mapper.readValue(contentAsString, User[].class);

        assertAll("Testing from a test-data.sql file to find users by ID ",
                () -> assertEquals(expectedLength, actualUser.length));


    }

    @Test
    void updateUser() throws Exception {
        String OldEmailAddress= "tolu2adetomiwa@gmail.com";
        String  updatedEmailAddress = "tolu2adetomiwa@gmail.com";

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User actualUser = mapper.readValue(contentAsString, User.class);

        actualUser.setEmail(updatedEmailAddress);

        ResultActions resultActions2 = this.mockMvc.perform(MockMvcRequestBuilders.put("/user/update" )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(actualUser))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions resultAction3 = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/{id}", 10)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result2 = resultAction3.andReturn();
        String contentAsString2 = result2.getResponse().getContentAsString();
        User updatedUser = mapper.readValue(contentAsString2, User.class);


     assertEquals(updatedEmailAddress, updatedUser.getEmail());
    }

    @Test
    void deleteUser() throws Exception {
        Long userIdToDelete = 10L;

        int expectedLengthAfterDeletion = 2;
        String  expectedUserEmail = "toluadetomiwa@gmail.com";
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}" ,userIdToDelete)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ResultActions resultAction2 = this.mockMvc.perform(MockMvcRequestBuilders.get("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultAction2.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User[] actualUserList = mapper.readValue(contentAsString, User[].class);

        assertAll("Testing from a test-data.sql file to find users by ID ",
                () -> assertEquals(expectedLengthAfterDeletion, actualUserList.length),
                () -> assertFalse(Arrays.stream(actualUserList)
                .anyMatch(user -> expectedUserEmail.equals(user.getEmail()))));



    }
}