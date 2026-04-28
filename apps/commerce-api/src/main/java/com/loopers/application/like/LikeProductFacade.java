package com.loopers.application.like;

import com.loopers.domain.like.LikeProductCommand;
import com.loopers.domain.like.LikeProductService;
import com.loopers.domain.like.LikeProductResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeProductFacade {

    private final LikeProductService likeProductService;

    public void like(LikeProductInput.Like input) {
        LikeProductCommand.Like command = input.toCommand();
        likeProductService.like(command);
    }

    public void unlike(LikeProductInput.Unlike input) {
        LikeProductCommand.Unlike command = input.toCommand();
        likeProductService.unlike(command);
    }

    public LikeProductOutput.GetLikedProducts getLikedProducts(String loginId) {
        LikeProductResult.GetLikedProducts result = likeProductService.getLikedProducts(loginId);
        return LikeProductOutput.GetLikedProducts.from(result);
    }
}
