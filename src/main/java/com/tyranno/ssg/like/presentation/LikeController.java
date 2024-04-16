package com.tyranno.ssg.like.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.like.application.LikeService;
import com.tyranno.ssg.like.dto.Request.LikeProductIdDto;
import com.tyranno.ssg.like.dto.Response.LikeListDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "좋아요 눌렀을때", description = "이미 좋아요 상태면 삭제, 아니면 좋아요")
    @PostMapping
    public ResponseEntity<?> modifyLLike(@RequestBody LikeProductIdDto likeProductIdDto,
                                         @RequestHeader(value = "Authorization", required = false) String token) {
        Long productId = likeProductIdDto.getProductId();
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            int isLike = likeService.modifyLike(productId, uuid);
            if (isLike == 11) {
                return new ResponseEntity<>(11);
            } else {
                return new ResponseEntity<>(99);
            }
        } else {
            throw new RuntimeException(ResponseStatus.ONLY_FOR_MEMBERS.getMessage());
        }
    }
    @Operation(summary = "좋아요 확인", description = "uuid로 좋아요 했는지 확인")
    @GetMapping("/isLike/{product_id}")
    public ResponseEntity<?> getLike(@PathVariable Long product_id,
                                     @RequestHeader(value = "Authorization", required = false) String token) {
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            int result = likeService.getLike(product_id, uuid);
            return new ResponseEntity<>(result);
        } else {
            int result = 99;
            return new ResponseEntity<>(result);
        }
    }
    @Operation(summary = "좋아요 리스트", description = "uuid")
    @GetMapping("/list")
    public ResponseEntity<?> getLikeList(@RequestHeader(value = "Authorization", required = false) String token,
                                         @RequestParam(defaultValue = "1") Integer page) {
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            LikeListDto likeListDto = likeService.getLikeList(uuid, page);
            return new ResponseEntity<>(likeListDto);
        } else {
            throw new RuntimeException(ResponseStatus.ONLY_FOR_MEMBERS.getMessage());
        }
    }
    @Operation(summary = "좋아요 삭제", description = "좋아요 상품 삭제하기")
    @DeleteMapping
    public ResponseEntity<?> deleteLike(@RequestBody LikeProductIdDto likeProductIdDto,
                                         @RequestHeader(value = "Authorization", required = false) String token) {
        Long productId = likeProductIdDto.getProductId();
        if(token != null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            int isLike = likeService.modifyLike(productId, uuid);
            if (isLike == 11) {
                return new ResponseEntity<>("좋아요 추가!");
            } else {
                return new ResponseEntity<>("좋아요 삭제!");
            }
        } else {
            throw new RuntimeException(ResponseStatus.ONLY_FOR_MEMBERS.getMessage());
        }
    }
}


