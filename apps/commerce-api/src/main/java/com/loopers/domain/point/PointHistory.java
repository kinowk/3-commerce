package com.loopers.domain.point;

import com.loopers.domain.point.attribute.PointHistoryType;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_histories")
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_history_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ref_point_id", nullable = false, updatable = false)
    private Long pointId;

    @Column(name = "ref_user_id", nullable = false, updatable = false)
    private Long userId;

    @Column(name = "amount", nullable = false, updatable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, updatable = false)
    private PointHistoryType type;

    @Column(name = "description")
    private String description;

    public PointHistory(Long pointId, Long userId, Long amount, PointHistoryType type, String description) {
        validatePointId(pointId);
        validateUserId(userId);
        validateAmount(amount);
        validateType(type);
        validateDescription(description);

        this.pointId = pointId;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.description = description;
    }

    private void validatePointId(Long pointId) {
        if (pointId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    private void validateUserId(Long userId) {
        if (userId == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    private void validateAmount(Long amount) {
        if (amount == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    private void validateType(PointHistoryType type) {
        if (type == null) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }

    private void validateDescription(String description) {
        if (!StringUtils.hasText(description)) {
            throw new CoreException(ErrorType.BAD_REQUEST);
        }
    }
}
