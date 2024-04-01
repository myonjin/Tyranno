package com.tyranno.ssg.cart.presentation;

import com.tyranno.ssg.cart.application.CartService;
import com.tyranno.ssg.cart.dto.Request.CartAddDto;
import com.tyranno.ssg.cart.dto.Request.CartCountModifyDto;
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

    @Operation(summary = "장바구니 담기 (상품 상세페이지)", description = "상품 상세페이지에서 장바구니에 상품을 담는다.")
    @PostMapping
    public ResponseEntity<?> addDeliveryAtDetail(@RequestBody CartAddDto cartAddDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        String result = cartService.addCartByOption(cartAddDto, uuid);
        return new ResponseEntity<>(result);
    }

    @Operation(summary = "장바구니 담기 (상품 리스트)", description = "상품 리스트에서 장바구니에 상품을 담는다.")
    @PostMapping("/{product_id}")
    public ResponseEntity<?> addDeliveryAtList(@PathVariable Long product_id, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        String result = cartService.addCartByProduct(product_id, uuid);
        return new ResponseEntity<>(result);
    }

    @Operation(summary = "장바구니 리스트 조회", description = "한 유저의 모든 장바구니 항목을 조회한다.(totalPrice 는 productPrice + extraPrice (discount 적용 x)")
    @GetMapping
    public ResponseEntity<?> deleteDelivery(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        return new ResponseEntity<>(cartService.getCartList(uuid));
    }

    @Operation(summary = "장바구니 선택항목 삭제", description = "선택 항목의 장바구니를 삭제한다.")
    @DeleteMapping
    public ResponseEntity<?> getDeliveryList(@RequestBody List<Long> cartDeleteList) {
        cartService.deleteCartList(cartDeleteList);
        return new ResponseEntity<>("선택한 장바구니 항목 삭제 완료");
    }

    @Operation(summary = "장바구니 삭제", description = "한 항목의 장바구니를 삭제한다.")
    @DeleteMapping("/{cart_id}")
    public ResponseEntity<?> getModifyView(@PathVariable Long cart_id) {
        cartService.deleteCart(cart_id);
        return new ResponseEntity<>("하나의 장바구니 삭제 완료");
    }

    @Operation(summary = "상품 수량 변경", description = "장바구니에 담긴 한 상품의 수량을 변경한다.")
    @PutMapping("/count")
    public ResponseEntity<?> modifyDelivery(@Valid @RequestBody CartCountModifyDto cartCountModifyDto) {
        cartService.modifyItemCount(cartCountModifyDto);
        return new ResponseEntity<>("장바구니 상품 수량 수정 완료");
    }


}
