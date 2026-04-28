package com.loopers.domain.like;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductResult {

    public record GetLikedProducts(
        List<Product> products
    ) {
        public static GetLikedProducts from(List<LikeProductQueryResult.GetLikedProducts> likedProducts) {
            List<Product> products = likedProducts.stream()
                    .map(p -> new Product(
                            p.likeProductId(),
                            p.userId(),
                            p.productId(),
                            p.productName()
                    ))
                    .toList();

            return new GetLikedProducts(products);
        }

        public record Product(
                Long likeProductId,
                Long userId,
                Long productId,
                String productName
        ) {

        }
    }
}
