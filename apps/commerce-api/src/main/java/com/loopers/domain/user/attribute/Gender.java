package com.loopers.domain.user.attribute;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    MAIL("남자"),
    FEMAIL("여자");

    private final String description;
}
