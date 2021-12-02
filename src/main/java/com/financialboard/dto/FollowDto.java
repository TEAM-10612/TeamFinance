package com.financialboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FollowDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FollowerInfo{

        private Long id;
        private String name;
        private Integer followState;
        private Integer loginUser;

        public FollowerInfo(String name, Integer followState, Integer loginUser) {
            this.name = name;
            this.followState = followState;
            this.loginUser = loginUser;
        }
    }
}
