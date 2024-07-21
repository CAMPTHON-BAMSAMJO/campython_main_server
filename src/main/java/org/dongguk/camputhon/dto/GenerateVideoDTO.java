package org.dongguk.camputhon.dto;

import lombok.Builder;
import lombok.Getter;
import org.dongguk.camputhon.domain.enums.Day;

import java.time.LocalTime;

@Builder
@Getter
public class GenerateVideoDTO {
    private int id;
    private String day;
    private String sex;
    private String start_time;
    private String end_time;
    private String activity;
    private String location;
    private String content;
}
