package com.crio.starter.exchange;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class ResponseDto {
  
  private String id;
  private String name;
  private String caption;
  private String url;
  private LocalDateTime createdAt;

}
