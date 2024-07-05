package com.tyranno.ssg.option.presentation;


import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.option.application.OptionService;
import com.tyranno.ssg.option.dto.OptionAbleListDto;
import com.tyranno.ssg.option.dto.OptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor  // final로 선언된 필드를 초기화 하는 생성자를 만들어준다.
@RestController
@Tag(name = "상품 옵션", description = "옵션 API")
@RequestMapping("/api/v1/option")
public class OptionController {

    private final OptionService optionService;

    @Operation(summary = "옵션 이름 조회", description = "한 옵션 id에 대한 이름들을 조회한다.")
    @GetMapping("/names/{option_id}")
    public ResponseEntity<?> getOptionNames(@PathVariable Long option_id) {
        return new ResponseEntity<>(optionService.getOptionNames(option_id));
    }

    @Operation(summary = "선택 가능 옵션 조회 (String)", description = "선택 가능한 옵션(문자)을 조회한다.")
    @GetMapping("/string/{product_id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "선택 가능 옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "선택 가능 옵션 조회 중 오류 발생")})
    public ResponseEntity<?> getOptions(@Parameter(description = "상품 ID") @PathVariable(value = "product_id") Long productId) {
        List<String> optionAble = optionService.getOptionAble(productId);

        return new ResponseEntity<>(optionAble);
    }

    @Operation(summary = "선택 가능 옵션 조회 (List)", description = "선택 가능한 옵션을 조회한다.")
    @GetMapping("/list/{product_id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "선택 가능 옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "선택 가능 옵션 조회 중 오류 발생")})
    public ResponseEntity<?> getOptionsList(@Parameter(description = "상품 ID") @PathVariable(value = "product_id") Long productId) {
        List<OptionAbleListDto> optionAbleList = optionService.getOptionAbleList(productId);

        return new ResponseEntity<>(optionAbleList);
    }

    @Tag(name = "상품 옵션", description = "옵션 API")
    @Operation(summary = "선택한 옵션 ID 조회 및 정보", description = "선택한 옵션을 조회한다..")
    @GetMapping("/{product_id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "옵션 조회 중 오류 발생")})
    public ResponseEntity<?> getOptionProduct(@Parameter(description = "상품 ID") @PathVariable(value = "product_id") Long productId,
                                              @RequestParam(value = "color", required = false) Long colorId,
                                              @RequestParam(value = "size", required = false) Long sizeId,
                                              @RequestParam(value = "extra", required = false) Long extraId,
                                              @RequestParam(value = "etc", required = false) Long etcId) {

        List<OptionDto> optionProduct = optionService.getOptionProduct(productId, colorId, sizeId, extraId, etcId);
        return new ResponseEntity<>(optionProduct);
    }

}
