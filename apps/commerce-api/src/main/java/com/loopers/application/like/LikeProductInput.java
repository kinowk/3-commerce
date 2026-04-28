package com.loopers.application.like;

import com.loopers.domain.like.LikeProductCommand;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductInput {

    public record Like(
            String loginId,
            Long productId
    ) {
        public LikeProductCommand.Like toCommand() {
            return new LikeProductCommand.Like(loginId, productId);
        }
    }

    public record Unlike(
            String loginId,
            Long productId
    ) {
        public LikeProductCommand.Unlike toCommand() {
            return new LikeProductCommand.Unlike(loginId, productId);
        }
    }
}
