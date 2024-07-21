package org.dongguk.camputhon.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dongguk.camputhon.apiPayload.code.BaseCode;
import org.dongguk.camputhon.apiPayload.code.ReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 일반적인 응답
    _OK(HttpStatus.OK, 200, "성공입니다."),

    // 유저 관련
    _CREATE_USER_OK(HttpStatus.OK, 200, "유저 생성 성공");

    // ~~~ 관련 응답

    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .build();
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return ReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(true)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}