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
//         @GetMapping("/find-options")
//         public List<Option> filter(
//                 @RequestParam(value = "category", required = false) String category,
//                 @RequestParam(value = "minPrice", required = false) Integer minPrice,
//                 @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
//                 @RequestParam(value = "price", required = false) Integer price,
//                 @RequestParam(value = "stock", required = false) Integer stock
//         ) {
//             log.info("category: {}, minPrice: {}, maxPrice: {}, price: {}, stock: {}", category, minPrice, maxPrice, price, stock);
//             return optionService.filter(category, minPrice, maxPrice, price, stock);
//         }
//@Slf4j
//@RequiredArgsConstructor
//@Tag(name = "공고", description = "공고 API")
//@RestController
//@RequestMapping("/posting")
//public class PostingController {
//
//    private final PostingService postingService;
//    @Tag(name = "공고")
//    @Operation(summary = "공고 전체 수", description = "공고 전체 수를 조회한다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "공고 전체 수를 조회 완료"),
//            @ApiResponse(responseCode = "400", description = "공고 전체 수를 조회 중 오류 발생"),
//            @ApiResponse(responseCode = "401", description = "로그인 필요")})
//    @GetMapping("/count")
//    private ResponseEntity<BasicResponse> countPosting() {
//        log.info("countPosting - Call");
//
//        try {
//            Integer postingCount = postingService.countPosting();
//            return ResponseEntity.ok().body(BasicResponse.Body(ResponseCode.SUCCESS, new HashMap<String, Integer>() {{ put("count", postingCount); }}));
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(BasicResponse.Body(ResponseCode.REGIST_FAIL, null));
//        }
//    }
//
//
//    @Tag(name = "공고")
//    @Operation(summary = "공고 등록", description = "공고를 등록한다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "공고 등록 완료"),
//            @ApiResponse(responseCode = "400", description = "공고 등록 중 오류 발생"),
//            @ApiResponse(responseCode = "401", description = "로그인 필요")})
//    @PostMapping("")
//    private ResponseEntity<BasicResponse> registPosting(@Parameter(description = "공고 등록을 위한 정보") @RequestBody BasicPostingRequest postingRegistRequest) {
//        log.info("registPosting - Call");
//
//        try {
//            postingService.registPosting(postingRegistRequest);
//            return ResponseEntity.ok().body(BasicResponse.Body(ResponseCode.SUCCESS, null));
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(BasicResponse.Body(ResponseCode.REGIST_FAIL, null));
//        }
//    }
//
//    @Tag(name = "공고")
//    @Operation(summary = "공고 목록 조회", description = "공고 전체 목록을 조회한다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "공고 목록 조회 완료"),
//            @ApiResponse(responseCode = "400", description = "공고 목록 조회 중 오류 발생")})
//    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
//    private ResponseEntity<BasicResponse> findAllPosting(@Parameter(description = "페이지", required = true) Integer page,
//                                                         @Parameter(description = "사이즈", required = true) Integer size,
//                                                         @Parameter(description = "프로젝트 주제") String subject,
//                                                         @Parameter(description = "지역 코드") String localCode,
//                                                         @Parameter(description = "분야 코드") String fieldCode,
//                                                         @Parameter(description = "기술 스택 목록", schema = @Schema(type = "List")) @RequestParam(required = false) List<String> postingSkillList) {
//        log.info("findAllPosting - Call");
//
//        Map<String, Object> searchKeys = new HashMap<>();
//        if (subject != null && !subject.equals("")) searchKeys.put("subject", subject);
//        if (localCode != null && !localCode.equals("")) searchKeys.put("localCode", localCode);
//        if (fieldCode != null && !fieldCode.equals("")) searchKeys.put("fieldCode", fieldCode);
//
//        try {
//            Map<String, Object> findAllPostingResponseList = postingService.findAllPosting(page, size, searchKeys, postingSkillList);
//            return ResponseEntity.ok().body(BasicResponse.Body(ResponseCode.SUCCESS, findAllPostingResponseList));
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(BasicResponse.Body(ResponseCode.LIST_NOT_FOUND, null));
//        }
//    }