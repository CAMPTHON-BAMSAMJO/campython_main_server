package org.dongguk.camputhon.dto;

import lombok.Builder;
import lombok.Getter;
import org.dongguk.camputhon.domain.enums.Day;

import java.time.LocalTime;

@Builder
@Getter
public class ShortRequestDTO {
    private Day day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String activity;
    private String location;
    private String content;
}
