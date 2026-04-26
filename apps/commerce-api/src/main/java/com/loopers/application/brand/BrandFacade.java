package com.loopers.application.brand;

import com.loopers.domain.brand.BrandResult;
import com.loopers.domain.brand.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BrandFacade {

    private final BrandService brandService;

    public BrandOutput.GetBrand getBrand(Long id) {
        BrandResult.GetBrand result = brandService.getBrand(id);
        return BrandOutput.GetBrand.from(result);
    }
}
