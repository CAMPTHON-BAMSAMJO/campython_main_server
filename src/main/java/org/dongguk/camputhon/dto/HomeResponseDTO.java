package org.dongguk.camputhon.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class HomeResponseDTO {

    @Getter
    @NoArgsConstructor
    public static class Home {
        private String shortUrl;
        private String shortType;
        private String advantage;
        private String develop;
        private List<Activity> activities;
        private List<Location> locations;
    }

    @Getter
    @NoArgsConstructor
    public static class Activity {
        private Long id;
        private String activity;
        private Long percentage;
        private Long timeSpent;

    }

    @Getter
    @NoArgsConstructor
    public static class Location {
        private Long id;
        private String location;
        private Long percentage;
        private Long timeSpent;
    }
}
