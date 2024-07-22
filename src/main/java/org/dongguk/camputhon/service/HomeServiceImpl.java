package org.dongguk.camputhon.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.Repository.ShortRepository;
import org.dongguk.camputhon.Repository.UserRepository;
import org.dongguk.camputhon.apiPayload.code.status.ErrorStatus;
import org.dongguk.camputhon.apiPayload.exception.handler.AppHandler;
import org.dongguk.camputhon.domain.Short;
import org.dongguk.camputhon.domain.User;

import java.util.Collections;
import java.util.Comparator;

import org.dongguk.camputhon.domain.enums.Activity;
import org.dongguk.camputhon.domain.enums.Location;
import org.dongguk.camputhon.dto.HomeResponseDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl {
    private final UserRepository userRepository;

    private final ShortRepository shortRepository;

    public HomeResponseDTO.Home getHomData(Long userId) {

        // 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        // 쇼츠 리스트 전체 조회
        List<Short> shortList = shortRepository.findByUser(user);

        AtomicInteger idGenerator = new AtomicInteger(1); // ID 생성기 초기화

        // 활동별 시간 집계, Enum 선언 순서대로 정렬
        Map<Activity, Long> activityTimeMap = shortList.stream()
                .collect(Collectors.groupingBy(
                        Short::getActivity,
                        Collectors.summingLong(s -> Duration.between(s.getStartAt(), s.getEndAt()).toMinutes())
                ));

        // 가장 많이 소비된 활동 찾기
        HomeResponseDTO.Activity mostSpentActivity = activityTimeMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> HomeResponseDTO.Activity.builder()
                        .id(idGenerator.getAndIncrement())
                        .activity(entry.getKey().name())
                        .timeSpent(entry.getValue())
                        .build())
                .orElseThrow(() -> new IllegalStateException("No activities found"));

        // 장소별 시간 집계
        Map<Location, Long> locationTimeMap = shortList.stream()
                .collect(Collectors.groupingBy(
                        Short::getLocation,
                        Collectors.summingLong(s -> Duration.between(s.getStartAt(), s.getEndAt()).toMinutes())
                ));

        // 가장 많이 소비된 장소 찾기
        HomeResponseDTO.Location mostSpentLocation = locationTimeMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> HomeResponseDTO.Location.builder()
                        .id(idGenerator.getAndIncrement())
                        .location(entry.getKey().name())
                        .timeSpent(entry.getValue())
                        .build())
                .orElseThrow(() -> new IllegalStateException("No locations found"));

        // Home 객체 생성 및 반환
        HomeResponseDTO.Home homeData = HomeResponseDTO.Home.builder()
                .shortImg(user.getTypeImg())
                .shortType(user.getShortType().getTypename())
                .advantage(user.getShortType().getAdvantage())
                .develop(user.getShortType().getDevelop())
                .mostActivity(mostSpentActivity) // 단일 활동 객체 사용
                .mostLocation(mostSpentLocation) // 단일 장소 객체 사용
                .build();

        return homeData;
    }
}
