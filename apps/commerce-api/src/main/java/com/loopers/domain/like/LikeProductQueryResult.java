package com.loopers.domain.like;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductQueryResult {
    public record GetLikedProducts(
            Long likeProductId,
            Long userId,
            Long productId,
            String productName
    ) {
    }

}
