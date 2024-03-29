package com.tyranno.ssg.order.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "주문", description = "Order API")
@RequestMapping("/api/v1/order")
public class OrderController {

    @Operation(summary = "주문 내역 조회", description = "주문 내역을 조회한다.")
    @GetMapping
    public ResponseEntity<?> getOrderList() {


        return new ResponseEntity<>("주문 내역 조회");
    }
}
