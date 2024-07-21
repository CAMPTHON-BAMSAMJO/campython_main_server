package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.apiPayload.ApiResponse;
import org.dongguk.camputhon.dto.UserResponseDTO;
import org.dongguk.camputhon.dto.UserUUIDRequestDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    // uuid로 유저 등록 컨트롤러
    public ApiResponse<UserResponseDTO> createUser(@RequestBody UserUUIDRequestDTO request) {
        return null;
    }
}
