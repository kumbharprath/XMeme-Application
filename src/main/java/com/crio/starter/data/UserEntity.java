package com.crio.starter.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Data
@Document(collection = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    private String id;
    
    @NonNull
    private String name;

    @NonNull
    private String caption;

    @NonNull
    private String url;

    @CreatedDate
    private LocalDateTime createdAt;
}
