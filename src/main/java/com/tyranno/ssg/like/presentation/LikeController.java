package com.tyranno.ssg.like.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.like.application.LikeService;
import com.tyranno.ssg.like.dto.LikeDto;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.dto.ProductInformationDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "좋아요", description = "좋아요 API")
@RequestMapping("/api/v1/like")
@Slf4j
public class LikeController {
    private final LikeService likeService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "리스트용 좋아요 정보", description = "상품 ID로 좋아요 정보, 없으면 null")
    @GetMapping("/likeInformation/{productId}")
    public ResponseEntity<?> getLikeInformation(@PathVariable(value = "productId") Long productId,
                                                @RequestHeader(value = "Authorization", required = false) String token) {
        LikeDto likeDto = null;
        if (token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            likeDto = likeService.getLikeByProductIdAndUsersId(productId, uuid);
        }
        return new ResponseEntity<>(likeDto);
    }

    @Operation(summary = "좋아요 눌렀을때", description = "이미 좋아요 상태면 삭제, 아니면 좋아요")
    @PostMapping("/likeButton/")
    public ResponseEntity<?> modifyLLike(@RequestParam Long productId,
                                         @RequestHeader(value = "Authorization", required = false) String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        boolean isLike = likeService.modifyLike(productId, uuid);
        if (isLike) {
            return new ResponseEntity<>("찜이 추가되었습니다.");
        } else {
            return new ResponseEntity<>("찜이 삭제되었습니다.");
        }
    }
}


