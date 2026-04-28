package com.loopers.domain.like;

import com.loopers.domain.product.ProductRepository;
import com.loopers.domain.user.User;
import com.loopers.domain.user.UserRepository;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final LikeProductRepository likeProductRepository;

    @Transactional
    public void like(LikeProductCommand.Like command) {
        User user = userRepository.findByLoginId(command.loginId())
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        Long userId = user.getId();
        Long productId = command.productId();
        productRepository.findById(productId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        likeProductRepository.saveIfAbsent(new LikeProduct(userId, productId));
    }

    @Transactional
    public void unlike(LikeProductCommand.Unlike command) {
        User user = userRepository.findByLoginId(command.loginId())
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        Long userId = user.getId();
        Long productId = command.productId();
        likeProductRepository.deleteIfPresent(new LikeProduct(userId, productId));
    }

    public LikeProductResult.GetLikedProducts getLikedProducts(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        List<LikeProductQueryResult.GetLikedProducts> likedProducts = likeProductRepository.findLikedProductsByUserId(user.getId());
        return LikeProductResult.GetLikedProducts.from(likedProducts);
    }
}
