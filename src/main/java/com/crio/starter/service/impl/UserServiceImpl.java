package com.crio.starter.service.impl;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.starter.data.UserEntity;
import com.crio.starter.exceptionhandler.DuplicateMemeException;
import com.crio.starter.exceptionhandler.ResourceNotFoundException;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.mapper.UserMapper;
import com.crio.starter.repository.UserRepository;
import com.crio.starter.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     * Creates a new Meme post.
     *
     * <p>This method first checks whether a meme with the same
     * name, URL, and caption already exists. If a duplicate is found,
     * a {@link DuplicateMemeException} is thrown.</p>
     *
     * @param requestDto the incoming meme request data
     * @return the created meme response
     * @throws DuplicateMemeException if the meme already exists
     */
    @Override
    public ResponseDto createMeme(RequestDto requestDto) {
        // Check for duplicate meme based on business rule
        boolean exists = userRepository.existsByNameAndUrlAndCaption(
                        requestDto.getName(), 
                        requestDto.getUrl(),
                        requestDto.getCaption());

        if(exists) throw new DuplicateMemeException("Duplicate meme post");

        // Convert DTO to entity and persist
        UserEntity entity = UserMapper.mapToEntity(requestDto);
        entity.setCreatedAt(LocalDateTime.now());
        UserEntity savedEntity = userRepository.save(entity);

        // Convert saved entity back to response DTO
        return UserMapper.mapToResponseDto(savedEntity);
    }


    /**
     * Retrieves the latest 100 Meme posts.
     *
     * <p>The memes are sorted in descending order of creation
     * time so that the most recent memes appear first.</p>
     *
     * @return a list of the latest 100 meme posts
     */
    @Override
    public List<ResponseDto> getAllMemes() {
        List<UserEntity> entities = userRepository.findTop100ByOrderByCreatedAtDesc();
        

        // Convert entities to DTOs, sort by creation time,
        // and limit the result to the latest 100 memes
        return entities.stream()
            .map(UserMapper::mapToResponseDto)
            .collect(Collectors.toList());
    }


    /**
     * Retrieves a Meme post by its unique identifier.
     *
     * <p>If the provided ID is null or the meme does not exist,
     * a {@link ResourceNotFoundException} is thrown.</p>
     *
     * @param id the unique ID of the meme post
     * @return the meme post as a {@link ResponseDto}
     * @throws ResourceNotFoundException if meme is not found
     */
    @Override
    public ResponseDto getMemeById(String id) {
        // Validate the incoming ID
        if (id == null) {
            throw new ResourceNotFoundException("Meme ID cannot be null");
        }
        
        UserEntity existingEntity = userRepository.findById(id)
                            .orElseThrow(() -> new ResourceNotFoundException("Meme post with an " + id + " not found!"));

        return UserMapper.mapToResponseDto(existingEntity);
    }
    
}
