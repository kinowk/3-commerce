package com.loopers.domain.like;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

class LikeProductTest {

    @DisplayName("LikeProduct 생성 시")
    @Nested
    class Create {

        @DisplayName("사용자ID가 null인 경우, 400 에러가 발생한다.")
        @ParameterizedTest
        @NullSource
        void throwsException_whenUserIdIsNull(Long userId) {
            // given
            Long productId = 1L;

            // when & then
            assertThatThrownBy(() -> new LikeProduct(userId, productId))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("상품ID가 null인 경우, 400 에러가 발생한다.")
        @ParameterizedTest
        @NullSource
        void throwsException_whenProductIdIsNull(Long productId) {
            //given
            Long userId = 1L;

            // when & then
            assertThatThrownBy(() -> new LikeProduct(userId, productId))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("성공적으로 LikeProduct가 생성된다.")
        void success() {
            // given
            Long userId = 1L;
            Long productId = 1L;

            // when
            LikeProduct likeProduct = new LikeProduct(userId, productId);

            // then
            assertThat(likeProduct.getUserId()).isEqualTo(userId);
            assertThat(likeProduct.getProductId()).isEqualTo(productId);
        }
    }
}
