package org.dongguk.camputhon.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dongguk.camputhon.Repository.TestRepository;
import org.dongguk.camputhon.domain.Test;
import org.dongguk.camputhon.dto.common.ResponseDto;
import org.dongguk.camputhon.exception.CommonException;
import org.dongguk.camputhon.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("")
public class TestController {
    private final TestRepository testRepository;

    @GetMapping("/test")
    public ResponseDto<?> test() {
        throw new CommonException(ErrorCode.TEST_ERROR);
    }

    @GetMapping("/hello")
    public ResponseDto<?> hello() {
        return ResponseDto.ok("Hello World!");
    }

    @GetMapping("/error")
    public ResponseDto<?> error() {
        throw new CommonException(ErrorCode.TEST_ERROR);
    }

    @PostMapping("/save")
    public ResponseDto<?> save() {
        Test test = Test.builder()
                .content("test")
                .build();
        test = testRepository.save(test);

        return ResponseDto.ok(test.getId());
    }

    @GetMapping("/get")
    public ResponseDto<?> get() {
        return ResponseDto.ok(testRepository.findAll());
    }
}
