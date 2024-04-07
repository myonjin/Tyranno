package com.tyranno.ssg.cart.presentation;

import com.tyranno.ssg.cart.application.CartService;
import com.tyranno.ssg.cart.dto.Request.*;
import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @Operation(summary = "장바구니 담기 (상품 상세페이지)", description = "상품 상세페이지에서 장바구니에 상품을 담는다.(isKeep 등록시 99로 들어감)")
    @PostMapping
    public ResponseEntity<?> addCartAtDetail(@RequestBody CartAddDto cartAddDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        String result = cartService.addCartByOption(cartAddDto, uuid);
        return new ResponseEntity<>(result);
    }

    @Operation(summary = "장바구니 담기 (상품 리스트)", description = "상품 리스트에서 장바구니에 상품을 담는다.")
    @PostMapping("/{product_id}")
    public ResponseEntity<?> addCartAtList(@PathVariable Long product_id, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        String result = cartService.addCartByProduct(product_id, uuid);
        return new ResponseEntity<>(result);
    }

    @Operation(summary = "장바구니 리스트 조회", description = "한 유저의 모든 장바구니 항목을 조회한다./api/v1/cart/names/{option_id} 으로 옵션 정보 조회 api 호출 필요")
    @GetMapping
    public ResponseEntity<?> getCartList(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(cartService.getCartList(uuid));
    }

    @Operation(summary = "장바구니 선택항목 삭제", description = "선택 항목의 장바구니를 삭제한다.")
    @DeleteMapping
    public ResponseEntity<?> deleteCartList(@RequestBody List<CartIdDto> cartDeleteList) {
        cartService.deleteCartList(cartDeleteList);
        return new ResponseEntity<>("선택한 장바구니 항목 삭제 완료");
    }

    @Operation(summary = "장바구니 삭제", description = "한 항목의 장바구니를 삭제한다.")
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<?> deleteCart(@PathVariable Long cart_id) {
        cartService.deleteCart(cart_id);
        return new ResponseEntity<>("하나의 장바구니 삭제 완료");
    }

    @Operation(summary = "장바구니 수량 변경", description = "장바구니에 담긴 한 상품의 수량을 변경한다.")
    @PutMapping("/count")
    public ResponseEntity<?> modifyCartCount(@RequestBody CartCountModifyDto cartCountModifyDto) {
        cartService.modifyCount(cartCountModifyDto);
        return new ResponseEntity<>("장바구니 상품 수량 수정 완료");
    }

    @Operation(summary = "장바구니 옵션 변경", description = "한 장바구니에 연결된 옵션을 변경한다.")
    @PutMapping("/option")
    public ResponseEntity<?> modifyCartOption(@RequestBody CartOptionModifyDto cartOptionModifyDto) {
        cartService.modifyOption(cartOptionModifyDto);
        return new ResponseEntity<>("장바구니 옵션 수정 완료");
    }

    @Operation(summary = "유저의 장바구니 갯수 조회", description = "한 유저에 담긴 장바구니 갯수를 조회한다.")
    @GetMapping("/users/count")
    public ResponseEntity<?> countUsersCart(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(cartService.getUsersCartCount(uuid));
    }

    @Operation(summary = "계속 담아두기 여부 변경", description = "한 장바구니의 계속 담아두기(isKeep) 여부를 변경한다. isKeep 는 Byte 타입")
    @PutMapping("/keep")
    public ResponseEntity<?> modifyCartIsKeep(@RequestBody CartKeepModifyDto cartKeepModifyDto) {
        cartService.modifyCartIsKeep(cartKeepModifyDto);
        return new ResponseEntity<>("계속 담아두기 여부가 변경되었습니다.");
    }


}
