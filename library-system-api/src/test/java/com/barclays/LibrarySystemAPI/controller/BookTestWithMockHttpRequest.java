package com.barclays.LibrarySystemAPI.controller;

import com.barclays.LibrarySystemAPI.model.Book;
import com.barclays.LibrarySystemAPI.model.Genre;
import com.barclays.LibrarySystemAPI.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.junit.jupiter.api.Assertions.*;

@Sql("classpath:test-data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})

class BookTestWithMockHttpRequest {

    @Autowired
    MockMvc mockMvc;


    ObjectMapper mapper =new ObjectMapper();
    ResultActions resultActions;

    @Test
    void searchBookByTitle() throws Exception {
        Long expectedBookId = 600L;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/book?title=Blah Blah sheep")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        System.out.println("response content " +contentAsString);
        Book[] actualBook =mapper.readValue(contentAsString, Book[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedBookId, actualBook[0].getId()));
    }

    @Test
    void findAllBooks() throws Exception {
        int expectedLength = 4;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] books = mapper.readValue(contentAsString, Book[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedLength, books.length),
                () -> assertEquals("Mirror Mirror", books[3].getTitle()));


    }

    @Test
    void searchByAuthor() throws Exception {
        int expectedLength = 2;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/book/author?name=Wole Shoyinka")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] books = mapper.readValue(contentAsString, Book[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedLength, books.length),
                () -> assertEquals(Genre.FICTION, books[0].getGenre()));

    }

    @Test
    void searchByGenre() throws Exception {
        int expectedLength = 2;
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/book/genre?genre=FICTION")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] books = mapper.readValue(contentAsString, Book[].class);

        assertAll("Testing from a test-data.sql file",
                () -> assertEquals(expectedLength, books.length),
                () -> assertEquals(800, books[1].getId()));


    }

    @Test
    void createBook() {
    }

    @Test
    void deleteBook() {
    }
}