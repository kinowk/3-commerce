package com.loopers.domain.point;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PointCommand {
    public record Charge(
            String loginId,
            Long amount
    ) {
    }

    public record Use(
            String loginId,
            Long amount
    ) {
    }
}
