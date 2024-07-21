package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.apiPayload.ApiResponse;
import org.dongguk.camputhon.apiPayload.code.status.SuccessStatus;
import org.dongguk.camputhon.dto.*;
import org.dongguk.camputhon.service.HomeServiceImpl;
import org.dongguk.camputhon.service.ShortListServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShortController {

    private final ShortListServiceImpl shortListService;
    private final HomeServiceImpl homeService;

    // 숏폼 생성 컨트롤러
    @PostMapping("/shorts")
    public ApiResponse<ShortResponseDTO> createShort(@RequestParam("userId") Long userId, @RequestBody ShortRequestDTO request) {
        ShortResponseDTO shortResponseDTO = shortListService.createShort(userId, request);
        return ApiResponse.of(SuccessStatus._CREATE_SHORT_OK, shortResponseDTO);
    }

    // 저장함 조회 컨트롤러
    @GetMapping("/shorts")
    public ApiResponse<ShortListResponseDTO.ShortList> getShortList(@RequestParam("userId") Long userId) {
        ShortListResponseDTO.ShortList shorts = shortListService.getShortList(userId);
        return ApiResponse.of(SuccessStatus._SHORT_LIST_OK, shorts);
    }

    // 홈 조회 컨트롤러
    @GetMapping("/home")
    public ApiResponse<HomeResponseDTO.Home> getHomeData(@RequestParam("userId") Long userId) {
        HomeResponseDTO.Home homeData = homeService.getHomData(userId);
        return ApiResponse.of(SuccessStatus._HOME_OK, homeData);
    }
}
