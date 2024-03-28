package com.tyranno.ssg.cart.presentation;

import com.tyranno.ssg.cart.application.CartService;
import com.tyranno.ssg.cart.dto.Request.CartAddDto;
import com.tyranno.ssg.delivery.application.DeliveryService;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "장바구니", description = "Cart API")
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "장바구니 담기", description = "새 배송지를 등록한다.")
    @PostMapping
    public ResponseEntity<?> addDelivery(@Valid @RequestBody CartAddDto cartAddDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        cartService.addCart(cartAddDto, uuid);
        return new ResponseEntity<>("배송지 등록 완료");
    }

    @Operation(summary = "장바구니 리스트 조회", description = "기존 등록된 배송지를 삭제한다.")
    @GetMapping
    public ResponseEntity<?> deleteDelivery(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        return new ResponseEntity<>( "배송지가 삭제되었습니다.");
    }

    @Operation(summary = "장바구니 선택항목 삭제", description = "한 유저에 대한 모든 배송지를 조회한다.")
    @DeleteMapping
    public ResponseEntity<?> getDeliveryList(@RequestBody List<Long> cartDeleteList) {


        return new ResponseEntity<>(deliveryList);
    }

    @Operation(summary = "장바구니 삭제", description = "배송지 수정화면에 기존 배송지 정보를 띄우기 위해 조회한다.")
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<?> getModifyView(@PathVariable Long cart_id) {


        return new ResponseEntity<>(singleDelivery);
    }

    @Operation(summary = "상품 수량 변경", description = "한 기존 배송지를 수정한다.")
    @PutMapping
    public ResponseEntity<?> modifyDelivery(@Valid @RequestBody DeliveryModifyDto deliveryModifyDto) {


        return new ResponseEntity<>("배송지 수정 완료");
    }


}
