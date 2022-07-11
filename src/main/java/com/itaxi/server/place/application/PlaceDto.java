package com.itaxi.server.place.application;

import lombok.*;
import com.itaxi.server.place.domain.Place;

public class PlaceDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class AddPlaceReq {
        private String name;
        private Long cnt = 0L;

        @Builder
        public AddPlaceReq(String name) {
            this.name = name;
            this.cnt = 0L;
        }

        public Place toEntity() {
            return Place.builder()
                    .name(this.name)
                    .cnt(this.cnt)
                    .build();
        }
    }
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdatePlaceReq {
        private String name;

        @Builder
        public UpdatePlaceReq(String name) {
            this.name = name;
        }

    }

    @Getter
    public static class DeletePlaceReq {
        private boolean deleted=true;

        @Builder
        public DeletePlaceReq() {
            this.deleted = true;
        }
    }

    @Getter
    public static class Res {
        private String name;
        private Long cnt;

        public Res(Place place) {
            this.name = place.getName();
            this.cnt = place.getCnt();
        }
    }
}
