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

    public PointResult.GetPoint getPoint(String loginId) {
        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        return pointRepository.findByUserId(user.getId())
                .map(PointResult.GetPoint::from)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));
    }

    @Transactional
    public PointResult.Charge charge(PointCommand.Charge command) {
        Long userId = command.userId();
        Long amount = command.amount();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        Point point = pointRepository.findByUserId(userId)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));

        point.charge(amount);
        Point savedPoint = pointRepository.save(point);

        PointHistory pointHistory = new PointHistory(savedPoint.getId(), userId, amount, PointHistoryType.USE, "포인트 사용");
        pointRepository.save(pointHistory);

        return PointResult.Charge.from(savedPoint);
    }
}
