package com.loopers.interfaces.api.like;

import com.loopers.application.like.LikeProductFacade;
import com.loopers.application.like.LikeProductInput;
import com.loopers.application.like.LikeProductOutput;
import com.loopers.interfaces.api.ApiHeader;
import com.loopers.interfaces.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/like")
@RequiredArgsConstructor
public class LikeProductProductV1Controller implements LikeProductV1ApiSpec {

    private final LikeProductFacade likeProductFacade;

    @PostMapping("/products/{productId}")
    public ApiResponse<Object> like(@RequestHeader(ApiHeader.X_USER_ID) String loginId, @PathVariable("productId") Long productId) {
        LikeProductInput.Like input = new LikeProductInput.Like(loginId, productId);
        likeProductFacade.like(input);
        return ApiResponse.success();
    }

    @DeleteMapping("/products/{productId}")
    public ApiResponse<Object> unlike(@RequestHeader(ApiHeader.X_USER_ID) String loginId, @PathVariable("productId") Long productId) {
        LikeProductInput.Unlike input = new LikeProductInput.Unlike(loginId, productId);
        likeProductFacade.unlike(input);
        return ApiResponse.success();
    }

    @GetMapping("/products")
    public ApiResponse<LikeProductResponse.GetLikedProducts> getLikedProducts(@RequestHeader(ApiHeader.X_USER_ID) String loginId) {
        LikeProductOutput.GetLikedProducts output = likeProductFacade.getLikedProducts(loginId);
        LikeProductResponse.GetLikedProducts response = LikeProductResponse.GetLikedProducts.from(output);
        return ApiResponse.success(response);
    }
}
