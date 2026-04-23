package com.loopers.domain.point;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointResult {
    public record GetPoint(
            Long id,
            Long userId,
            Long balance
    ) {
        public static GetPoint from(Point point) {
            return new GetPoint(
                    point.getId(),
                    point.getUserId(),
                    point.getBalance()
            );
        }
    }

    public record Charge(
            Long id,
            Long userId,
            Long balance,
            Long amount
    ) {
        public static Charge of(Point point, Long amount) {
            return new Charge(
                    point.getId(),
                    point.getUserId(),
                    point.getBalance(),
                    amount
            );
        }
    }

    public record Use(
            Long id,
            Long userId,
            Long balance,
            Long amount
    ) {
        public static Use of(Point point, Long amount) {
            return new Use(
                    point.getId(),
                    point.getUserId(),
                    point.getBalance(),
                    amount
            );
        }
    }
}
