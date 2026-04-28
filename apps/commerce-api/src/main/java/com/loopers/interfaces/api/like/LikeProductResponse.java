package com.loopers.interfaces.api.like;

import com.loopers.application.like.LikeProductOutput;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductResponse {
    public record Like(
            Long productId,
            Long likeCount
    ) {
    }

    public record Unlike(
            Long productId,
            Long likeCount
    ) {
    }

    public record GetLikedProducts(
            List<Product> products
    ) {
        public static GetLikedProducts from(LikeProductOutput.GetLikedProducts output) {
            List<Product> products = output.products().stream()
                    .map(p -> new Product(
                            p.productId(),
                            p.productName()
                    ))
                    .toList();

            return new GetLikedProducts(
                    products
            );
        }

        public record Product(
                Long productId,
                String productName
        ) {

        }
    }
}
