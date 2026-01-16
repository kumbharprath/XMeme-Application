package com.crio.starter.repository;

import java.util.List;
import com.crio.starter.data.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String>{
    /**
     * function chceck if the post already exists wih given name, url and caption
     * 
     * @return true if post exists else false
     */
    boolean existsByNameAndUrlAndCaption(
        String name,
        String url,
        String caption
    );

    List<UserEntity> findTop100ByOrderByCreatedAtDesc();
}
