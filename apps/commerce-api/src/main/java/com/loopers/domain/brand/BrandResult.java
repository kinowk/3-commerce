package com.loopers.domain.brand;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandResult {

    public record GetBrand(
        String name,
        String description
    ) {
        public static GetBrand from(Brand brand) {
            return new GetBrand(
                    brand.getName(),
                    brand.getDescription()
            );
        }
    }
}
