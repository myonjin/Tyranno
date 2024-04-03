package com.tyranno.ssg.recent.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.product.dto.DiscountDto;
import com.tyranno.ssg.recent.application.RecentService;
import com.tyranno.ssg.recent.dto.RecentViewedDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "최근 본 상품", description = "최근 본 상품 API")
@RequestMapping("/api/v1/recent")
@Slf4j
public class RecentController {

    private final RecentService recentService;

    @Operation(summary = "최근 본 상품 조회", description = "uuid로 DB의 상품 목록 조회")
    @GetMapping("/user")
    public ResponseEntity<?> getRecentByUuid(String uuid) {
        RecentViewedDto recentViewedDto = recentService.getRecentByUser(uuid);
        return new ResponseEntity<>(recentViewedDto);
    }
}
