package com.tyranno.ssg.delivery.presentation;

import com.tyranno.ssg.delivery.application.DeliveryService;
import com.tyranno.ssg.delivery.dto.*;
import com.tyranno.ssg.delivery.dto.response.BaseDeliveryInfoDto;
import com.tyranno.ssg.delivery.dto.response.OrderDeliveryInfoDto;
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
@Tag(name = "배송지", description = "Delivery API")
@RequestMapping("/api/v1/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "배송지 등록", description = "새 배송지를 등록한다.")
    @PostMapping
    public ResponseEntity<?> addDelivery(@Valid @RequestBody DeliveryAddDto deliveryAddDto, @RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        deliveryService.addDelivery(deliveryAddDto, uuid);

        return new ResponseEntity<>("배송지 등록 완료");
    }

    @Operation(summary = "배송지 삭제", description = "기존 등록된 배송지를 삭제한다.")
    @DeleteMapping("/{delivery_id}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Long delivery_id) {

        deliveryService.deleteDelivery(delivery_id);

        return new ResponseEntity<>("배송지가 삭제되었습니다.");
    }

    @Operation(summary = "배송지 목록 조회", description = "한 유저에 대한 모든 배송지를 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<?> getDeliveryList(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);
        List<DeliveryListDto> deliveryList = deliveryService.getDeliveryList(uuid);

        return new ResponseEntity<>(deliveryList);
    }

    @Operation(summary = "기본 배송지 정보 조회", description = "한 유저의 기본배송지의 별칭, 우편주소, 주소기본, 주소상세를 조회한다.")
    @GetMapping("/base")
    public ResponseEntity<?> getBaseDeliveryInfo(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        return new ResponseEntity<>(deliveryService.getBaseDeliveryInfo(uuid));
    }

    @Operation(summary = "주문 배송지 정보 조회", description = "주문 시 기본배송지의 정보를 조회한다.")
    @GetMapping("/order")
    public ResponseEntity<OrderDeliveryInfoDto> getOrderDeliveryInfo(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        return new ResponseEntity<>(deliveryService.getOrderDeliveryInfo(uuid));
    }

    @Operation(summary = "배송지 수정 화면 조회", description = "배송지 수정화면에 기존 배송지 정보를 띄우기 위해 조회한다.")
    @GetMapping("/modify-view/{delivery_id}")
    public ResponseEntity<?> getModifyView(@PathVariable Long delivery_id) {

        DeliveryDetailDto Delivery = deliveryService.getDetailDelivery(delivery_id);

        return new ResponseEntity<>(Delivery);
    }

    @Operation(summary = "배송지 수정", description = "한 기존 배송지를 수정한다.")
    @PutMapping
    public ResponseEntity<?> modifyDelivery(@Valid @RequestBody DeliveryModifyDto deliveryModifyDto) {

        deliveryService.modifyDelivery(deliveryModifyDto);

        return new ResponseEntity<>("배송지 수정 완료");
    }

    @Operation(summary = "기본 배송지 변경", description = "한 유저의 기존 배송지 지정을 변경한다.(기존배송지로 지정하려는 delivery의 id 입력)")
    @PutMapping("/change-base")
    public ResponseEntity<?> modifyBaseDelivery(@RequestBody BaseDeliveryModifyDto baseDeliveryModifyDto) {

        deliveryService.modifyBaseDelivery(baseDeliveryModifyDto);

        return new ResponseEntity<>("기본배송지로 설정되었습니다.");
    }

    @Operation(summary = "기본 배송지 별칭 조회", description = "한 유저의 기존 배송지 별칭을 조회한다.")
    @GetMapping("/base-name")
    public ResponseEntity<?> getBaseDeliveryName(@RequestHeader("Authorization") String token) {
        String uuid = jwtTokenProvider.tokenToUuid(token);

        return new ResponseEntity<>(deliveryService.getBaseDeliveryName(uuid));
    }
}
