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
    @GetMapping("/likeInformation/{productId}/{usersId}")
    public ResponseEntity<?> getLikeInformation(@PathVariable(value = "productId") Long productId,
                                                @RequestHeader(value = "Authorization", required = false) String token) {
        LikeDto likeDto = null;
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            likeDto = likeService.getLikeByProductIdAndUsersId(productId, uuid);
        }
        return new ResponseEntity<>(likeDto);
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> testToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        log.info(token);
//        return new ResponseEntity<>(token);
//    }
}

