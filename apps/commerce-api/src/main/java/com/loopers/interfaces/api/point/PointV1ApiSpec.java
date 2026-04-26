package com.loopers.interfaces.api.point;

import com.loopers.interfaces.api.ApiHeader;
import com.loopers.interfaces.api.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Point V1 API", description = "포인트 관련 API")
public interface PointV1ApiSpec {

    @Operation(
            summary = "보유 포인트 조회",
            description = "보유한 포인트를 조회합니다."
    )
    ApiResponse<PointResponse.GetPoint> getPoint(
            @Schema(name = ApiHeader.X_USER_ID, description = "사용자 로그인 ID") String loginId
    );

    @Operation(
            summary = "포인트 충전",
            description = "포인트를 충전합니다."
    )
    ApiResponse<PointResponse.Charge> charge(
            @Schema(name = ApiHeader.X_USER_ID, description = "사용자 로그인 ID") String loginId,
            @RequestBody(description = "충전 금액") PointRequest.Charge request
    );

    @Operation(
            summary = "포인트 사용",
            description = "포인트를 사용합니다."
    )
    ApiResponse<PointResponse.Use> use(
        @Schema(name = ApiHeader.X_USER_ID, description = "사용자 로그인 ID") String loginId,
        @RequestBody(description = "사용 금액") PointRequest.Use request
    );
}
