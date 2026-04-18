package com.loopers.domain.point;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ref_user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "balance", nullable = false)
    private Long balance;

    public Point(Long userId, Long balance) {
        validateBalance(balance);

        this.userId = userId;
        this.balance = balance;
    }

    private void validateBalance(Long balance) {
        if (balance == null || balance < 0) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    public Long charge(Long amount) {
        if (amount == null || amount <= 0) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }

        if (Long.MAX_VALUE - amount < balance) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }

        balance += amount;

        return balance;
    }

    public Long deduct(Long amount) {
        if (amount == null || amount <= 0) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }

        if (!hasSufficientBalance(amount)) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }

        balance -= amount;

        return balance;
    }

    public boolean hasSufficientBalance(Long amount) {
        return amount != null && amount > 0 && balance >= amount;
    }
}
