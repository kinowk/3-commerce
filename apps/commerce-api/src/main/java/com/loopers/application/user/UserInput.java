package com.loopers.application.user;

import com.loopers.domain.user.UserCommand;
import com.loopers.domain.user.attribute.Gender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInput {

    public record Join(
            String username,
            String loginId,
            String password,
            String email,
            String birthDate,
            Gender gendeer
    ) {

        public UserCommand.Join toCommand() {
            return new UserCommand.Join(
                    username,
                    loginId,
                    password,
                    email,
                    birthDate,
                    gendeer
            );
        }
    }

    public record GetUser(
            String loginId
    ) {
    }
}
