package com.loopers.domain.point;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @DisplayName("포인트 생성 시")
    @Nested
    class Create {

        @DisplayName("잔액이 0보다 작으면, 400 에러가 발생한다.")
        @ParameterizedTest
        @ValueSource(longs = {
                -1,
                -100
        })
        void throwsException_whenInvalidBalance(Long balance) {
            //given
            Long userId = 1L;

            //when & then
            assertThatThrownBy(() -> new Point(userId, balance))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("잔액이 null인 경우, 400 에러가 발생한다.")
        @Test
        void throwsException_whenInvalidBalanceIsNull() {
            //given
            Long userId = 1L;
            Long balance = null;

            //when & then
            assertThatThrownBy(() -> new Point(userId, balance))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("성공적으로 포인트를 생성한다.")
        @Test
        void success() {
            //given
            Long userId = 1L;
            Long balance = 1000L;

            //when
            Point point = new Point(userId, balance);

            //then
            assertThat(point.getUserId()).isEqualTo(userId);
            assertThat(point.getBalance()).isEqualTo(balance);
        }
    }

    @DisplayName("포인트 충전 시")
    @Nested
    class Charge {

        @DisplayName("충전 금액이 0 이하이면, 400 에러가 발생한다.")
        @ParameterizedTest
        @ValueSource(longs = {
                0,
                -1,
                -100
        })
        void throwsException_whenAmountIsZeroOrLess(Long amount) {
            //given
            Point point = new Point(1L, 1000L);

            //when & then
            assertThatThrownBy(() -> point.charge(amount))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("충전 후 잔액이 Long 최대값을 초과하면, 400 에러가 발생한다.")
        @Test
        void throwsException_whenBalanceOverflows() {
            //given
            Point point = new Point(1L, Long.MAX_VALUE - 10);
            Long amount = 11L;

            //when & then
            assertThatThrownBy(() -> point.charge(amount))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("성공적으로 포인트를 충전한다.")
        @Test
        void success() {
            //given
            Point point = new Point(1L, 1000L);
            Long amount = 500L;

            //when
            Long result = point.charge(amount);

            //then
            assertThat(result).isEqualTo(1500L);
            assertThat(point.getBalance()).isEqualTo(1500L);
        }
    }

    @DisplayName("포인트 차감 시")
    @Nested
    class Deduct {

        @DisplayName("차감 금액이 0 이하이면, 400 에러가 발생한다.")
        @ParameterizedTest
        @ValueSource(longs = {
                0,
                -1,
                -100
        })
        void throwsException_whenAmountIsZeroOrLess(Long amount) {
            //given
            Point point = new Point(1L, 1000L);

            //when & then
            assertThatThrownBy(() -> point.deduct(amount))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("잔액이 부족하면, 400 에러가 발생한다.")
        @Test
        void throwsException_whenInsufficientBalance() {
            //given
            Point point = new Point(1L, 100L);
            Long amount = 101L;

            //when & then
            assertThatThrownBy(() -> point.deduct(amount))
                    .isInstanceOf(CoreException.class)
                    .extracting("errorType")
                    .isEqualTo(ErrorType.BAD_REQUEST);
        }

        @DisplayName("성공적으로 포인트를 차감한다.")
        @Test
        void success() {
            //given
            Point point = new Point(1L, 1000L);
            Long amount = 500L;

            //when
            Long result = point.deduct(amount);

            //then
            assertThat(result).isEqualTo(500L);
            assertThat(point.getBalance()).isEqualTo(500L);
        }
    }

    @DisplayName("잔액 확인 시")
    @Nested
    class HasSufficientBalance {

        @Test
        @DisplayName("잔액이 충분하면 true를 반환한다.")
        void returnsTrue_whenBalanceIsSufficient() {
            //given
            Point point = new Point(1L, 1000L);

            //when & then
            assertThat(point.hasSufficientBalance(500L)).isTrue();
            assertThat(point.hasSufficientBalance(1000L)).isTrue();
        }

        @Test
        @DisplayName("잔액이 부족하면 false를 반환한다.")
        void returnsFalse_whenBalanceIsInsufficient() {
            //given
            Point point = new Point(1L, 1000L);

            //when & then
            assertThat(point.hasSufficientBalance(1001L)).isFalse();
        }
    }
}
