package com.loopers.domain.point;

import com.loopers.domain.point.attribute.PointHistoryType;
import com.loopers.domain.user.User;
import com.loopers.domain.user.UserRepository;
import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PointService {

    private final UserRepository userRepository;
    private final PointRepository pointRepository;

    public PointResult.GetPoint getPoint(Long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        return pointRepository.findByUserId(userId)
                .map(PointResult.GetPoint::from)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));
    }

    @Transactional
    public PointResult.Charge charge(PointCommand.Charge command) {
        Long userId = command.userId();
        Long amount = command.amount();

        userRepository.findById(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        point.charge(amount);
        Point savedPoint = pointRepository.save(point);

        PointHistory pointHistory = new PointHistory(savedPoint.getId(), userId, amount, PointHistoryType.USE, "포인트 사용");
        pointRepository.save(pointHistory);

        return PointResult.Charge.of(savedPoint, amount);
    }

    @Transactional
    public PointResult.Use use(PointCommand.Use command) {
        Long userId = command.userId();
        Long amount = command.amount();

        userRepository.findById(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));
        point.deduct(amount);

        PointHistory pointHistory = new PointHistory(point.getId(), userId, amount, PointHistoryType.USE, "포인트 사용");
        pointRepository.save(pointHistory);

        return PointResult.Use.of(point, amount);
    }
}
