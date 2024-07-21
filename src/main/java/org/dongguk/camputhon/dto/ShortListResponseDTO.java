package org.dongguk.camputhon.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ShortListResponseDTO {

    @Getter
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

        private String activity;

        private String location;

        private Long timeSpent;

        private String createdAt;
    }
}
