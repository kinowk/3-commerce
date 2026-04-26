package com.loopers.interfaces.api.brand;

import com.loopers.application.brand.BrandOutput;

public class BrandResponse {
    public record GetBrand(
            String name,
            String description
    ) {
        public static GetBrand from(BrandOutput.GetBrand output) {
            return new GetBrand(
                    output.name(),
                    output.description()
            );
        }
    }
}
