package com.loopers.domain.like;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductCommand {

    public record Like(
            String loginId,
            Long productId
    ) {
    }

    public record Unlike(
            String loginId,
            Long productId
    ) {
    }

}
