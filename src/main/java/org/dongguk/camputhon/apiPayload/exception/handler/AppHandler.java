package org.dongguk.camputhon.apiPayload.exception.handler;

import org.dongguk.camputhon.apiPayload.code.BaseErrorCode;
import org.dongguk.camputhon.apiPayload.exception.GeneralException;

public class AppHandler extends GeneralException {
    public AppHandler(BaseErrorCode code) {
        super(code);
    }

}
