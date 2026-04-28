package com.loopers.application.like;

import com.loopers.domain.like.LikeProductResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProductOutput {

    public record GetLikedProducts(
            List<Product> products
    ) {
        public static GetLikedProducts from(LikeProductResult.GetLikedProducts result) {
            List<Product> products = result.products().stream()
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
