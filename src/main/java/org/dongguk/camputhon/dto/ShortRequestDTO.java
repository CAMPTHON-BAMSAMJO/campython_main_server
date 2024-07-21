package org.dongguk.camputhon.dto;

import lombok.Builder;
@Builder
public class ShortRequestDTO {
    private String day;
    private String sex;
    private String startTime;
    private String endTime;
    private String activity;
    private String location;
    private String content;

}
