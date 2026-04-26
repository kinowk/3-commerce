package com.loopers.interfaces.api.point;

import com.loopers.application.point.PointOutput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointResponse {
    public record GetPoint(
            Long id,
            String loginId,
            Long balance

    ) {
        public static GetPoint from(PointOutput.GetPoint output) {
            return new GetPoint(
                    output.id(),
                    output.loginId(),
                    output.balance()
            );
        }
    }

    public record Charge(
            Long id,
            String loginId,
            Long balance,
            Long amount
    ) {
        public static Charge from(PointOutput.Charge output) {
            return new Charge(
                    output.id(),
                    output.loginId(),
                    output.balance(),
                    output.amount()
            );
        }
    }

    public record Use(
            Long id,
            String loginId,
            Long balance,
            Long amount
    ) {
        public static Use from(PointOutput.Use output) {
            return new Use(
                    output.id(),
                    output.loginId(),
                    output.balance(),
                    output.amount()
            );
        }
    }
}
