package com.loopers.domain.point;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointResult {
    public record GetPoint(
            Long userId,
            Long balance
    ) {
        public static GetPoint from(Point point) {
            return new GetPoint(
                    point.getUserId(),
                    point.getUserId()
            );
        }
    }

    public record Charge(
            Long userId,
            Long balance
    ) {
        public static Charge from(Point point) {
            return new Charge(
                    point.getUserId(),
                    point.getBalance()
            );
        }
    }

    public record Use(
            Long userId,
            Long balance
    ) {
        public static Use from(Point point) {
            return new Use(
                    point.getUserId(),
                    point.getBalance()
            );
        }
    }
}
