package com.loopers.application.brand;

import com.loopers.domain.brand.BrandResult;

public class BrandOutput {

    public record GetBrand(
            String name,
            String description
    ) {
        public static GetBrand from(BrandResult.GetBrand result) {
            return new GetBrand(
                    result.name(),
                    result.description()
            );
        }
    }
}
