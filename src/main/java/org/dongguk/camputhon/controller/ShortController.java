package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.apiPayload.ApiResponse;
import org.dongguk.camputhon.dto.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortController {

    // 숏폼 생성 컨트롤러
    public ApiResponse<String> createShort(@RequestParam UserUUIDRequestDTO uuid, @RequestBody ShortRequestDTO request) {
        return null;
    }

    // 저장함 조회 컨트롤러
    public ApiResponse<ShortListResponseDTO> getShortList(@RequestParam UserUUIDRequestDTO uuid) {
        return null;
    }

    // 홈 조회 컨트롤러
    public ApiResponse<HomeResponseDTO> getHomeData(@RequestParam UserUUIDRequestDTO uuid) {
        return null;
    }
}
