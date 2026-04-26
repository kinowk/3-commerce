package com.loopers.domain.like;

import com.loopers.domain.BaseEntity;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "likes")
public class Like extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ref_user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "ref_product_id", nullable = false, updatable = false)
    private Long productId;

    public Like(Long userId, Long productId) {
        validateUserId(userId);
        validateProductId(productId);

        this.userId = userId;
        this.productId = productId;
    }

    private void validateUserId(Long userId) {
        if (userId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    private void validateProductId(Long productId) {
        if (productId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }
}
