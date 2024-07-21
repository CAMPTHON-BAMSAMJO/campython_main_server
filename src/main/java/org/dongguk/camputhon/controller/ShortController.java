package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.apiPayload.ApiResponse;
import org.dongguk.camputhon.apiPayload.code.status.SuccessStatus;
import org.dongguk.camputhon.dto.*;
import org.dongguk.camputhon.service.HomeServiceImpl;
import org.dongguk.camputhon.service.ShortListServiceImpl;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortController {

    private final ShortListServiceImpl shortListService;
    private final HomeServiceImpl homeService;

    // 숏폼 생성 컨트롤러
    public ApiResponse<String> createShort(@RequestParam("userId") Long userId, @RequestBody ShortRequestDTO request) {
        return null;
    }

    // 저장함 조회 컨트롤러
    public ApiResponse<ShortListResponseDTO.ShortList> getShortList(@RequestParam("userId") Long userId) {
        ShortListResponseDTO.ShortList shorts = shortListService.getShortList(userId);
        return ApiResponse.of(SuccessStatus._SHORT_LIST_OK, shorts);
    }

    // 홈 조회 컨트롤러
    public ApiResponse<HomeResponseDTO.Home> getHomeData(@RequestParam("userId") Long userId) {
        HomeResponseDTO.Home homeData = homeService.getHomData(userId);
        return ApiResponse.of(SuccessStatus._HOME_OK, homeData);
    }
}
