package com.loopers.interfaces.api.brand;

import com.loopers.application.brand.BrandFacade;
import com.loopers.application.brand.BrandOutput;
import com.loopers.interfaces.api.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/brands")
@RequiredArgsConstructor
public class BrandV1Controller implements BrandV1ApiSpec {

    private final BrandFacade brandFacade;

    @GetMapping("/{brandId}")
    public ApiResponse<BrandResponse.GetBrand> getBrand(@PathVariable("brandId") Long brandId) {
        BrandOutput.GetBrand output = brandFacade.getBrand(brandId);
        BrandResponse.GetBrand response = BrandResponse.GetBrand.from(output);
        return ApiResponse.success(response);
    }
}
