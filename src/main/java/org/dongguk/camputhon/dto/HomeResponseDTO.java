package org.dongguk.camputhon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dongguk.camputhon.domain.enums.ShortType;

import java.util.List;

public class HomeResponseDTO {

    @Getter
    @Builder
    public static class Home {
        private String shortImg;
        private String shortType;
        private String advantage;
        private String develop;
        private Activity mostActivity;
        private Location mostLocation;
    }

    @Getter
    @Builder
    public static class Activity {
        private int id;
        private String activity;
        private Long percentage;
        private Long timeSpent;
    }

    @Getter
    @Builder
    public static class Location {
        private int id;
        private String location;
        private Long percentage;
        private Long timeSpent;
    }
}
