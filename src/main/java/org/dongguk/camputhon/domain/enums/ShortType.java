package org.dongguk.camputhon.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ShortType {
    NONE("NONE", "장점 설명", "단점 설명"),
    THINKING("THINKING", "장점 설명", "단점 설명"),
    SUNSET("SUNSET", "장점 설명", "단점 설명"),
    SUNSHINE("SUNSHINE", "장점 설명", "단점 설명"),
    HARDWORK("HARDWORK", "장점 설명", "단점 설명"),
    SWEAT("SWEAT", "장점 설명", "단점 설명"),
    LAUGHTER("LAUGHTER", "장점 설명", "단점 설명"),
    MEETING("MEETING", "장점 설명", "단점 설명"),
    DREAMS("DREAMS", "장점 설명", "단점 설명");

    private final String typename;
    private final String advantage;
    private final String develop;

}
