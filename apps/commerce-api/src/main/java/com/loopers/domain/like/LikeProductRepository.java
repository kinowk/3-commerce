package com.loopers.domain.like;

import java.util.List;
import java.util.Optional;

public interface LikeProductRepository {

    LikeProduct save(LikeProduct likeProduct);

    Optional<LikeProduct> findOne(Long userId, Long productId);

    void saveIfAbsent(LikeProduct likeProduct);

    void deleteIfPresent(LikeProduct likeProduct);

    List<LikeProductQueryResult.GetLikedProducts> findLikedProductsByUserId(Long userId);
}
