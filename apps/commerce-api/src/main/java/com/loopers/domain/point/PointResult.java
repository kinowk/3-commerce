package com.loopers.domain.point;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointResult {
    public record GetPoint(
            Long id,
            String loginId,
            Long balance
    ) {
        public static GetPoint of(Point point, String loginId) {
            return new GetPoint(
                    point.getId(),
                    loginId,
                    point.getBalance()
            );
        }
    }

    public record Charge(
            Long id,
            String loginId,
            Long balance,
            Long amount
    ) {
        public static Charge of(Point point, String loginId, Long amount) {
            return new Charge(
                    point.getId(),
                    loginId,
                    point.getBalance(),
                    amount
            );
        }
    }

    public record Use(
            Long id,
            String loginId,
            Long balance,
            Long amount
    ) {
        public static Use of(Point point, String loginId, Long amount) {
            return new Use(
                    point.getId(),
                    loginId,
                    point.getBalance(),
                    amount
            );
        }
    }
}
