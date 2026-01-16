package com.crio.starter.service;

import java.util.List;
import com.crio.starter.exchange.RequestDto;
import com.crio.starter.exchange.ResponseDto;

public interface UserService {
    ResponseDto createMeme(RequestDto requestDto);
    List<ResponseDto> getAllMemes();
    ResponseDto getMemeById(String id);
}
