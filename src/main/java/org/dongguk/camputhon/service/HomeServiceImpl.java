package org.dongguk.camputhon.service;

import lombok.RequiredArgsConstructor;
import org.dongguk.camputhon.Repository.ShortRepository;
import org.dongguk.camputhon.Repository.UserRepository;
import org.dongguk.camputhon.apiPayload.code.status.ErrorStatus;
import org.dongguk.camputhon.apiPayload.exception.handler.AppHandler;
import org.dongguk.camputhon.domain.Short;
import org.dongguk.camputhon.domain.User;
import java.util.Comparator;
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
    UserRepository userRepository;

    ShortRepository shortRepository;

    public HomeResponseDTO.Home getHomData(Long userId) {

        // 유저 찾기
        User user = userRepository.findById(userId).orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        // 쇼츠 리스트 전체 조회
        List<Short> shortList = shortRepository.findByUser(user);

        AtomicInteger idGenerator = new AtomicInteger(1); // ID 생성기 초기화

        // 활동별 시간 집계
        List<HomeResponseDTO.Activity> activities = shortList.stream()
                .collect(Collectors.groupingBy(
                        Short::getActivity,
                        Collectors.summingLong(s -> Duration.between(s.getStartAt(), s.getEndAt()).toMinutes())
                ))
                .entrySet().stream()
                .map(entry -> HomeResponseDTO.Activity.builder()
                        .id(idGenerator.getAndIncrement())
                        .activity(entry.getKey().name())
                        .timeSpent(entry.getValue())
                        .build())
                .sorted(Comparator.comparingLong(HomeResponseDTO.Activity::getTimeSpent))
                .collect(Collectors.toList());

        // 장소별 시간 집계
        List<HomeResponseDTO.Location> locations = shortList.stream()
                .collect(Collectors.groupingBy(
                        Short::getLocation,
                        Collectors.summingLong(s -> Duration.between(s.getStartAt(), s.getEndAt()).toMinutes())
                ))
                .entrySet().stream()
                .map(entry -> HomeResponseDTO.Location.builder()
                        .id(idGenerator.getAndIncrement())
                        .location(entry.getKey().name())
                        .timeSpent(entry.getValue())
                        .build())
                .sorted(Comparator.comparingLong(HomeResponseDTO.Location::getTimeSpent))
                .collect(Collectors.toList());

        // Home 객체 생성 및 반환
        HomeResponseDTO.Home homeData = HomeResponseDTO.Home.builder()
                .shortImg(user.getTypeImg())
                .shortType(user.getShortType().getTypename())
                .advantage(user.getShortType().getAdvantage())
                .develop(user.getShortType().getDevelop())
                .activities(activities)
                .locations(locations)
                .build();

        return homeData;
    }
}
