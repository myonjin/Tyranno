<<<<<<< HEAD
package com.tyranno.ssg.like.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.like.application.LikeService;
import com.tyranno.ssg.like.dto.LikeDto;
import com.tyranno.ssg.product.application.ProductService;
import com.tyranno.ssg.product.dto.ProductInformationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "좋아요", description = "좋아요 API")
@RequestMapping("/api/v1/like")
@Slf4j
public class LikeController {
    private final LikeService likeService;

    @Operation(summary = "리스트용 좋아요 정보", description = "상품 ID로 좋아요 정보, 없으면 null")
    @GetMapping("/likeInformation/{productId}/{usersId}")
    public ResponseEntity<?> getLikeInformation(@PathVariable(value = "productId") Long productId,
                                                @PathVariable(value = "usersId") Long usersId) {
        LikeDto likeDto = likeService.getLikeByProductIdAndUsersId(productId, usersId);
        return new ResponseEntity<>(likeDto);
    }

}
=======
//package com.tyranno.ssg.like.presentation;
//
//import com.tyranno.ssg.delivery.application.DeliveryService;
//import com.tyranno.ssg.delivery.dto.*;
//import com.tyranno.ssg.global.ResponseEntity;
//import com.tyranno.ssg.security.JwtTokenProvider;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@Tag(name = "배송지", description = "Delivery API")
//@RequestMapping("/api/v1/like")
//public class LikeController {
//
//    private final DeliveryService deliveryService;
//    private final JwtTokenProvider jwtTokenProvider;
//
//    @Operation(summary = "배송지 등록", description = "새 배송지를 등록한다.")
//    @PostMapping
//    public ResponseEntity<?> addDelivery(@Valid @RequestBody DeliveryAddDto deliveryAddDto, @RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.tokenToUuid(token);
//        deliveryService.addDelivery(deliveryAddDto,uuid);
//
//        return new ResponseEntity<>("배송지 등록 완료");
//    }
//
//    @Operation(summary = "배송지 삭제", description = "기존 등록된 배송지를 삭제한다.")
//    @DeleteMapping("/{delivery_id}")
//    public ResponseEntity<?> deleteDelivery(@PathVariable Long delivery_id) {
//
//        deliveryService.deleteDelivery(delivery_id);
//
//        return new ResponseEntity<>( "배송지가 삭제되었습니다.");
//    }
//
//    @Operation(summary = "배송지 목록 조회", description = "한 유저에 대한 모든 배송지를 조회한다.")
//    @GetMapping("/list")
//    public ResponseEntity<?> getDeliveryList(@RequestHeader("Authorization") String token) {
//        String uuid = jwtTokenProvider.tokenToUuid(token);
//        List<DeliveryListDto> deliveryList = deliveryService.getDeliveryList(uuid);
//
//        return new ResponseEntity<>(deliveryList);
//    }
//
//    @Operation(summary = "배송지 수정 화면 조회", description = "배송지 수정화면에 기존 배송지 정보를 띄우기 위해 조회한다.")
//    @GetMapping("/modifyView/{delivery_id}")
//    public ResponseEntity<?> getModifyView(@PathVariable Long delivery_id) {
//
//        DeliveryDetailDto singleDelivery = deliveryService.getDetailDelivery(delivery_id);
//
//        return new ResponseEntity<>(singleDelivery);
//    }
//
//    @Operation(summary = "배송지 수정", description = "한 기존 배송지를 수정한다.")
//    @PutMapping
//    public ResponseEntity<?> modifyDelivery(@Valid @RequestBody DeliveryModifyDto deliveryModifyDto) {
//
//        deliveryService.modifyDelivery(deliveryModifyDto);
//
//        return new ResponseEntity<>("배송지 수정 완료");
//    }
//
//    @Operation(summary = "기본 배송지 변경", description = "한 유저의 기존 배송지 지정을 변경한다.")
//    @PutMapping("/changeDefault")
//    public ResponseEntity<?> modifyBaseDelivery(@Valid @RequestBody BaseDeliveryModifyDto baseDeliveryModifyDto) {
//
//        deliveryService.modifyBaseDelivery(baseDeliveryModifyDto);
//
//        return new ResponseEntity<>("기본배송지로 설정되었습니다.");
//    }
//
//
//}
>>>>>>> cb5a1269a42d364d47354014b302c03e3bee4d94
