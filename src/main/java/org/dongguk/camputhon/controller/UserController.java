package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.apiPayload.ApiResponse;
import org.dongguk.camputhon.apiPayload.code.status.SuccessStatus;
import org.dongguk.camputhon.dto.UserUUIDRequestDTO;
import org.dongguk.camputhon.service.UserServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    // uuid로 유저 등록 컨트롤러
    public ApiResponse<Long> createUser(@RequestBody UserUUIDRequestDTO request) {
        Long id = userService.createUser(request);
        return ApiResponse.of(SuccessStatus._OK, id);
    }
}
