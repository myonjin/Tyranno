package com.tyranno.ssg.vendor.presentation;

import com.tyranno.ssg.product.dto.ProductDetailDto;
import com.tyranno.ssg.vendor.application.VendorService;
import com.tyranno.ssg.vendor.dto.VendorDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/vendor")
@Slf4j
public class VendorController {
    private final VendorService vendorService;

    @Operation(summary = "Vendor 정보", description = "productId에 대한 vendor정보 불러오기", tags = { "Get Vendor" })
    @GetMapping("/detail/{productId}")
    public ResponseEntity<VendorDto> vendorDetail(@PathVariable Long productId) {

        VendorDto vendorDto = vendorService.findByProductId(productId);

        if (productDetailDto != null) {
            log.info("product ID: {}", productId);
            return ResponseEntity.ok(productDetailDto);
        } else {
            log.warn("product ID 없는거다: {}", productId);
            return ResponseEntity.notFound().build();
        }
    }
}
