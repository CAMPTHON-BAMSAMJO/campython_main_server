package org.dongguk.camputhon.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.camputhon.Repository.ShortRepository;
import org.dongguk.camputhon.Repository.UserRepository;
import org.dongguk.camputhon.apiPayload.code.status.ErrorStatus;
import org.dongguk.camputhon.apiPayload.exception.handler.AppHandler;
import org.dongguk.camputhon.domain.Short;
import org.dongguk.camputhon.domain.User;
import org.dongguk.camputhon.domain.enums.Activity;
import org.dongguk.camputhon.domain.enums.Location;
import org.dongguk.camputhon.dto.GenerateVideoDTO;
import org.dongguk.camputhon.dto.ShortListResponseDTO;
import org.dongguk.camputhon.dto.ShortRequestDTO;
import org.dongguk.camputhon.dto.ShortResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortListServiceImpl {
    private final ShortRepository shortRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public ShortResponseDTO createShort(Long userId, ShortRequestDTO shortRequestDTO) {
        // 유저 검색
        User user = userRepository.findById(userId).orElseThrow(() -> new AppHandler(ErrorStatus.NOT_FOUND_USER));

        Short shortform = Short.builder()
                .user(user)
                .startAt(shortRequestDTO.getStartTime())
                .endAt(shortRequestDTO.getEndTime())
                .content(shortRequestDTO.getContent())
                .activity(Activity.valueOf(shortRequestDTO.getActivity()))
                .location(Location.valueOf(shortRequestDTO.getLocation()))
                .build();

        shortform = shortRepository.save(shortform);

        String url = "http://13.124.1.27:8000/ttest";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        GenerateVideoDTO generateVideoDTO = GenerateVideoDTO.builder()
                .id(shortform.getId().intValue())
                .start_time(shortRequestDTO.getStartTime().toString())
                .end_time(shortRequestDTO.getEndTime().toString())
                .day(shortRequestDTO.getDay().toString())
                .sex(user.getSex().toString().concat("자"))
                .activity(shortRequestDTO.getActivity())
                .location(shortRequestDTO.getLocation())
                .content(shortRequestDTO.getContent())
                .build();

        // ObjectMapper를 사용하여 DTO를 JSON 문자열로 변환
        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = null;
        try {
            requestJson = objectMapper.writeValueAsString(generateVideoDTO);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        log.info(response.toString());
        log.info(response.getBody());

        String s3Url = Objects.requireNonNull(response.getBody()).replace("\"", "");

        shortform.updateUrl(s3Url);

        shortRepository.save(shortform);

        return ShortResponseDTO.builder()
                .s3Url(s3Url)
                .build();
    }

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
                        .shortType(user.getShortType().getTypename())
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
