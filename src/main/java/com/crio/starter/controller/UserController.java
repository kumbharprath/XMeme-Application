package com.crio.starter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.UserService;


/**
 * REST Controller responsible for handling HTTP requests
 * related to Meme posts.
 *
 * <p>This controller exposes APIs to create memes,
 * fetch all memes, and fetch a meme by its ID.</p>
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/memes")
@Tag(name = "Meme API", description = "Endpoints for managing memes")
public class UserController {
    
    private final UserService userService;

    /**
     * Creates a new Meme post.
     *
     * <p>This endpoint accepts meme data in the request body
     * and delegates the creation logic to the service layer.
     * On successful creation, it returns the ID of the
     * newly created meme with HTTP status 201 (CREATED).</p>
     *
     * @param requestDto the request payload containing meme details
     * @return ResponseEntity containing the created meme ID
     */
    @PostMapping
    @Operation(summary = "Create a new meme post")
    public ResponseEntity<Map<String, String>> createMemePost(@Valid @RequestBody RequestDto requestDto) {
        ResponseDto post = userService.createMeme(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(Map.of("id", post.getId()));
    }


    /**
     * Retrieves the latest Meme posts.
     *
     * <p>This endpoint returns a list of the most recent
     * meme posts. The list is already sorted and limited
     * at the service layer.</p>
     *
     * @return ResponseEntity containing a list of meme posts
     */
    @GetMapping
    @Operation(summary = "Get latest 100 meme posts")
    public ResponseEntity<List<ResponseDto>> getMemePosts() {
        List<ResponseDto> latestMemePosts = userService.getAllMemes();
        return ResponseEntity.ok(latestMemePosts);
    }

    /**
     * Retrieves a Meme post by its unique identifier.
     *
     * <p>If a meme with the given ID does not exist,
     * a ResourceNotFoundException is thrown by the service layer
     * and translated into HTTP 404 response.</p>
     *
     * @param id the unique ID of the meme post
     * @return ResponseEntity containing the meme post data
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a meme post by ID")
    public ResponseEntity<ResponseDto> getMemePost(@PathVariable String id) {
        ResponseDto memePost = userService.getMemeById(id);
        return ResponseEntity.ok(memePost);
    }

}
