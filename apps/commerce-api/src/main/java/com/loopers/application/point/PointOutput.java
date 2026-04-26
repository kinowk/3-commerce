package com.loopers.application.point;

import com.loopers.domain.point.PointResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointOutput {
    public record GetPoint(
            Long id,
            String loginId,
            Long balance
    ) {
        public static GetPoint from(PointResult.GetPoint result) {
            return new GetPoint(
                    result.id(),
                    result.loginId(),
                    result.balance()
            );
        }
    }

    public record Charge(
            Long id,
            Long userId,
            Long balance,
            Long amount
    ) {
        public static Charge from(PointResult.Charge result) {
            return new Charge(
                    result.id(),
                    result.userId(),
                    result.balance(),
                    result.amount()
            );
        }
    }

    public record Use(
            Long id,
            Long userId,
            Long balance,
            Long amount
    ) {
        public static Use from(PointResult.Use result) {
            return new Use(
                    result.id(),
                    result.userId(),
                    result.balance(),
                    result.amount()
            );
        }
    }
}
