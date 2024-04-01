package com.tyranno.ssg.order.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.order.application.OrderService;
import com.tyranno.ssg.order.dto.OrderAddDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "주문", description = "Order API")
@RequestMapping("/api/v1/order")
public class OrderController {
    private final JwtTokenProvider jwtTokenProvider;
    private final OrderService orderService;

    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회한다.")
    @GetMapping
    public ResponseEntity<?> getOrderList(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>("주문 내역 조회");
    }

    @Operation(summary = "주문 생성", description = "주문을 생성한다.")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestHeader("Authorization") String token, @RequestBody OrderAddDto orderAddDto) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        orderService.addOrderList(orderAddDto, uuid);
        return new ResponseEntity<>("주문 생성");
    }

    //비회원 주문 생성
    @Operation(summary = "비회원 주문 생성", description = "비회원 주문을 생성한다.")
    @PostMapping("/non-member")
    public ResponseEntity<?> createNonMemberOrder(@RequestBody OrderAddDto orderAddDto) {
        orderService.addOrderList(orderAddDto, "nonmember");
        return new ResponseEntity<>("비회원 주문 생성");
    }
}
