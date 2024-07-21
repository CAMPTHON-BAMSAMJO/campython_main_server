package org.dongguk.camputhon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.dongguk.camputhon.domain.enums.Activity;
import org.dongguk.camputhon.domain.enums.Location;
import org.dongguk.camputhon.domain.enums.ShortType;

import java.time.LocalDateTime;
import java.util.List;

public class ShortListResponseDTO {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ShortList {
        private List<ShortDetail> shorts;
    }

    @Getter
    @Builder
    public static class ShortDetail {
        private Long id;

        private String shortImg;

        private String shortType;

        private String shortUrl;

        private Activity activity;

        private Location location;

        private Long timeSpent;

        private LocalDateTime createdAt;
    }
}
