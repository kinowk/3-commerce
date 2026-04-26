package com.loopers.domain.brand;

import com.loopers.support.error.CoreException;
import com.loopers.support.error.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandResult.GetBrand getBrand(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new CoreException(ErrorType.NOT_FOUND));
        return BrandResult.GetBrand.from(brand);
    }
}
