package com.crio.starter.exchange;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;



@Builder
@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class RequestDto {
  @NotBlank
  private String name;

  @NotBlank
  private String caption;

  @NotBlank
  private String url;
}
