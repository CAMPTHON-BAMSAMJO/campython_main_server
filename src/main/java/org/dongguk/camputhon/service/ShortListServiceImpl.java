package org.dongguk.camputhon.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.Repository.ShortRepository;
import org.dongguk.camputhon.Repository.UserRepository;
import org.dongguk.camputhon.apiPayload.code.status.ErrorStatus;
import org.dongguk.camputhon.apiPayload.exception.handler.AppHandler;
import org.dongguk.camputhon.domain.Short;
import org.dongguk.camputhon.domain.User;
import org.dongguk.camputhon.dto.ShortListResponseDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortListServiceImpl {
    ShortRepository shortRepository;
    UserRepository userRepository;

    // 쇼츠 리스트 정렬 서비스
    public ShortListResponseDTO.ShortList getShortList(Long userId) {
        // 유저 검색
        User user = userRepository.findById(userId).orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        // 쇼츠 리스트 전체 조회
        List<Short> shortList = shortRepository.findByUser(user);

        // DTO로 데이터 변환
        List<ShortListResponseDTO.ShortDetail> shortDetails = shortList.stream()
                .map(shortEntity -> ShortListResponseDTO.ShortDetail.builder()
                        .id(shortEntity.getId())
                        .shortImg(user.getTypeImg())
                        .shortType(user.getShortType())
                        .shortUrl(shortEntity.getShortUrl())
                        .activity(shortEntity.getActivity())
                        .location(shortEntity.getLocation())
                        .timeSpent(Duration.between(shortEntity.getStartAt(), shortEntity.getEndAt()).toMinutes()) // startAt과 endAt 사이의 분 계산
                        .createdAt(shortEntity.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        // 리스트 패키징
        ShortListResponseDTO.ShortList shorts = new ShortListResponseDTO.ShortList();
        shorts.setShorts(shortDetails);

        return shorts;
    }

}
