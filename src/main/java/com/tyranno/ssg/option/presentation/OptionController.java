package com.tyranno.ssg.option.presentation;


import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.option.application.OptionService;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.dto.OptionAbleListDto;
import com.tyranno.ssg.option.dto.OptionDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor  // final로 선언된 필드를 초기화 하는 생성자를 만들어준다.
@RestController
@Tag(name = "상품 옵션", description = "옵션 API")
@RequestMapping("/api/v1/option")
public class OptionController {

    private final OptionService optionService;


    @Operation(summary = "선택 가능 옵션 조회 (String)", description = "선택 가능한 옵션(문자)을 조회한다.")
    @GetMapping("/string/{productId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "선택 가능 옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "선택 가능 옵션 조회 중 오류 발생")})
    public ResponseEntity<?> findOptions(@Parameter(description = "상품 ID") @PathVariable(value = "productId") Long productId) {
        List<String> optionAble = optionService.findOptionAble(productId);

        return new ResponseEntity<>(optionAble);
    }

    @Tag(name = "상품 옵션", description = "옵션 API")
    @Operation(summary = "선택 가능 옵션 조회 (List)", description = "선택 가능한 옵션을 조회한다.")
    @GetMapping("/list/{productId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "선택 가능 옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "선택 가능 옵션 조회 중 오류 발생")})
    public ResponseEntity<?> findOptionsList(@Parameter(description = "상품 ID") @PathVariable(value = "productId") Long productId) {
        List<OptionAbleListDto> optionAbleList = optionService.findOptionAbleList(productId);

        return new ResponseEntity<>(optionAbleList);
    }

    @Tag(name = "상품 옵션", description = "옵션 API")
    @Operation(summary = "선택한 옵션 ID 조회 및 정보", description = "선택한 옵션을 조회한다..")
    @GetMapping("/{productId}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "옵션 조회 완료"),
            @ApiResponse(responseCode = "400", description = "옵션 조회 중 오류 발생")})
    public ResponseEntity<?> getOptionProduct(@Parameter(description = "상품 ID") @PathVariable(value = "productId") Long productId,
                                               @RequestParam(value = "color", required = false) Long colorId,
                                               @RequestParam(value = "size", required = false) Long sizeId,
                                               @RequestParam(value = "extra", required = false) Long extraId,
                                               @RequestParam(value = "etc", required = false) Long etcId){

        List<OptionDto> optionProduct = optionService.getOptionProduct(productId, colorId, sizeId, extraId, etcId);
        return new ResponseEntity<>(optionProduct);
    }

}
