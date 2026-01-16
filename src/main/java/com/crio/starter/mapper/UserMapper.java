package com.crio.starter.mapper;

import com.crio.starter.data.UserEntity;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;

public class UserMapper {
    public static UserEntity mapToEntity(RequestDto requestDto) {
        return UserEntity.builder()
            .name(requestDto.getName())
            .url(requestDto.getUrl())
            .caption(requestDto.getCaption())
            .build();
    }

    public static ResponseDto mapToResponseDto(UserEntity user) {
        return ResponseDto.builder()
            .id(user.getId())
            .name(user.getName())
            .url(user.getUrl())
            .caption(user.getCaption())
            .createdAt(user.getCreatedAt())
            .build();
    }
}
