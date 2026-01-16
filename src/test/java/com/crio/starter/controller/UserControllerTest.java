package com.crio.starter.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Controller layer tests for UserController.
 * Uses MockMvc to test REST endpoints.
 */
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Test POST /api/memes endpoint.
     */
    @Test
    void createMemePost_success() throws Exception {
        RequestDto requestDto = new RequestDto();
        requestDto.setName("John");
        requestDto.setUrl("http://image.com/meme.jpg");
        requestDto.setCaption("Funny meme");

        ResponseDto responseDto = new ResponseDto();
        responseDto.setId("1");

        when(userService.createMeme(any(RequestDto.class)))
                .thenReturn(responseDto);

        mockMvc.perform(post("/memes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"));
    }

    /**
     * Test GET /api/memes endpoint.
     */
    @Test
    void getMemePosts_success() throws Exception {
        when(userService.getAllMemes())
                .thenReturn(java.util.List.of(new ResponseDto()));

        mockMvc.perform(get("/memes"))
                .andExpect(status().isOk());
    }

    /**
     * Test GET /api/memes/{id} endpoint.
     */
    @Test
    void getMemePostById_success() throws Exception {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId("1");

        when(userService.getMemeById("1"))
                .thenReturn(responseDto);

        mockMvc.perform(get("/memes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }
}

