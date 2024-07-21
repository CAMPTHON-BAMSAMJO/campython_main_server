package org.dongguk.camputhon.dto;

import lombok.Builder;
import org.dongguk.camputhon.domain.enums.Day;

@Builder
public class ShortRequestDTO {
    private Day day;
    private String sex;
    private String startTime;
    private String endTime;
    private String activity;
    private String location;
    private String content;

}
