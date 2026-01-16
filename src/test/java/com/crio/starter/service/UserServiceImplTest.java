package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.crio.starter.data.UserEntity;
import com.crio.starter.exceptionhandler.DuplicateMemeException;
import com.crio.starter.exceptionhandler.ResourceNotFoundException;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.UserRepository;
import com.crio.starter.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Unit tests for UserServiceImpl.
 * Uses Mockito to mock repository behavior.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private RequestDto requestDto;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        requestDto = new RequestDto();
        requestDto.setName("John");
        requestDto.setUrl("http://image.com/meme.jpg");
        requestDto.setCaption("Funny meme");

        userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setName("John");
        userEntity.setUrl("http://image.com/meme.jpg");
        userEntity.setCaption("Funny meme");
    }

    /**
     * Test successful meme creation.
     */
    @Test
    void createMeme_success() {
        when(userRepository.existsByNameAndUrlAndCaption(
                requestDto.getName(),
                requestDto.getUrl(),
                requestDto.getCaption()))
                .thenReturn(false);

        when(userRepository.save(any(UserEntity.class)))
                .thenReturn(userEntity);

        ResponseDto response = userService.createMeme(requestDto);

        assertNotNull(response);
        assertEquals("1", response.getId());
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    /**
     * Test duplicate meme creation.
     */
    @Test
    void createMeme_duplicate_shouldThrowException() {
        when(userRepository.existsByNameAndUrlAndCaption(
                requestDto.getName(),
                requestDto.getUrl(),
                requestDto.getCaption()))
                .thenReturn(true);

        assertThrows(DuplicateMemeException.class,
                () -> userService.createMeme(requestDto));

        verify(userRepository, never()).save(any());
    }

    /**
     * Test fetching all memes (limited to 100).
     */
    @Test
    void getAllMemes_success() {
        when(userRepository.findTop100ByOrderByCreatedAtDesc())
                .thenReturn(Arrays.asList(userEntity));

        List<ResponseDto> memes = userService.getAllMemes();

        assertEquals(1, memes.size());
        verify(userRepository, times(1)).findTop100ByOrderByCreatedAtDesc();
    }

    /**
     * Test fetching meme by ID successfully.
     */
    @Test
    void getMemeById_success() {
        when(userRepository.findById("1"))
                .thenReturn(Optional.of(userEntity));

        ResponseDto response = userService.getMemeById("1");

        assertNotNull(response);
        assertEquals("1", response.getId());
    }

    /**
     * Test fetching meme by ID when not found.
     */
    @Test
    void getMemeById_notFound() {
        when(userRepository.findById("2"))
                .thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> userService.getMemeById("2"));
    }
}

