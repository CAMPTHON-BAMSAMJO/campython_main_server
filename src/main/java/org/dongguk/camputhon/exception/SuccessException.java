package org.dongguk.camputhon.exception;

public class SuccessException extends RuntimeException {
    private final SuccessCode code;

    public SuccessException(SuccessCode successCode) {
        this.code = successCode;
    }

    public SuccessCode getSuccessCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return code.getMessage();
    }
}
