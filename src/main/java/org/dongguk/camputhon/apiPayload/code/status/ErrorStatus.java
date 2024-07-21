package org.dongguk.camputhon.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.dongguk.camputhon.apiPayload.code.BaseErrorCode;
import org.dongguk.camputhon.apiPayload.code.ErrorReasonDTO;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,400,"잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,401,"인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, 402, "금지된 요청입니다."),

    // Ror test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, 4001, "이거는 테스트"),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST, 40401, "존재하지 않는 유저입니다.");


    // ~~~ 관련 응답 ....


    private final HttpStatus httpStatus;
    private final int code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}