package com.loopers.interfaces.api.user;

import com.loopers.application.user.UserFacade;
import com.loopers.application.user.UserInput;
import com.loopers.application.user.UserOutput;
import com.loopers.interfaces.api.ApiHeader;
import com.loopers.interfaces.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/api/v1/users")
@RequiredArgsConstructor
public class UserV1Controller {

    private final UserFacade userFacade;

    @PostMapping
    public ApiResponse<UserResponse.Join> join(@RequestBody UserRequest.Join request) {
        UserInput.Join input = request.toInput();
        UserOutput.Join output = userFacade.join(input);
        UserResponse.Join response = UserResponse.Join.from(output);
        return ApiResponse.success(response);
    }

    @GetMapping("/{loginId}")
    public ApiResponse<UserResponse.GetUser> getUser(@PathVariable("loginId") String loginId) {
        UserOutput.GetUser output = userFacade.getUser(loginId);
        UserResponse.GetUser response = UserResponse.GetUser.from(output);
        return ApiResponse.success(response);
    }

    @GetMapping("/me")
    public ApiResponse<UserResponse.GetUser> getCurrentUser(@RequestHeader(ApiHeader.X_USER_ID) String loginId) {
        UserOutput.GetUser output = userFacade.getUser(loginId);
        UserResponse.GetUser response = UserResponse.GetUser.from(output);
        return ApiResponse.success(response);
    }
}
