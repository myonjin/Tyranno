package com.tyranno.ssg.order.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.order.application.OrderService;
import com.tyranno.ssg.order.dto.OrderAddDto;
import com.tyranno.ssg.order.dto.OrderListDto;
import com.tyranno.ssg.order.dto.ResponseNonOrderDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@RestController
@Tag(name = "주문", description = "Order API")
@RequestMapping("/api/v1/order")
@Slf4j
public class OrderController {
    private final JwtTokenProvider jwtTokenProvider;
    private final OrderService orderService;

    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회한다.")
    @GetMapping
    public ResponseEntity<List<OrderListDto>> getOrderList(@RequestHeader(value = "Authorization",required = false) String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        // 주문내역 없을경우 에러처리
        if (uuid == null) {
            return new ResponseEntity<>(null);
        }
        List<OrderListDto> orderListDto = orderService.getOrderList(uuid);

        return new ResponseEntity<>(orderListDto);
    }

    @Operation(summary = "주문 생성", description = "주문을 생성한다.")
    @PostMapping
    public ResponseEntity<String> createOrder(@RequestHeader("Authorization") String token, @RequestBody OrderAddDto orderAddDto) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        orderService.addOrderList(orderAddDto, uuid);
        return new ResponseEntity<>("주문 생성");
    }
    /**
     * 비회원 주문 생성
     * @uuid nonmember
     */
    //비회원 주문 생성
    @Operation(summary = "비회원 주문 생성", description = "비회원 주문을 생성한다.")
    @PostMapping("/non-member/creat")
    public ResponseEntity<String> createNonMemberOrder(@RequestBody OrderAddDto orderAddDto) {
        int randomNumber = new Random().nextInt(900000) + 100000;
        orderService.addOrderList(orderAddDto, randomNumber +"");
        return new ResponseEntity<>("비회원 주문 생성");
    }
    //비회원 조회
    @Operation(summary = "비회원 주문 내역 조회", description = "비회원 주문 내역을 조회한다.")
    @PostMapping("/non-member")
    public ResponseEntity<List<OrderListDto>> getOrderList(@RequestBody ResponseNonOrderDto responseNonOrderDto) {
        List<OrderListDto> orderListDto = orderService.getOrderList(responseNonOrderDto);
        return new ResponseEntity<>(orderListDto);
    }


}
