package com.tyranno.ssg.search.presentation;

import com.tyranno.ssg.global.ResponseEntity;
import com.tyranno.ssg.search.application.SearchService;
import com.tyranno.ssg.search.dto.searchProductIdListDto;
import com.tyranno.ssg.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Tag(name = "검색", description = "검색 API")
@RequestMapping("/api/v1/search")
@Slf4j
public class SearchController {
    private final SearchService searchService;
    private final JwtTokenProvider jwtTokenProvider;
    @Operation(summary = "검색하기", description = "검색에 해당하는 productIdList 반환")
    @GetMapping("/{keyword}")
    public ResponseEntity<?> searchKeyword(@PathVariable String keyword,
                                           @RequestParam(defaultValue = "0") Integer sortCriterion,
                                           @RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(required = false) Integer maxPrice,
                                           @RequestParam(required = false) Integer minPrice,
                                           @RequestParam(required = false) Float minRate,
                                           @RequestHeader(value = "Authorization", required = false) String token) {
        searchProductIdListDto searchProductIdListDto = searchService.getProductIdList(sortCriterion, page, keyword,
                maxPrice, minPrice, minRate);
        if(token != null & maxPrice == null & minPrice == null & minRate == null) {
            String uuid = jwtTokenProvider.tokenToUuid(token);
            searchService.addRecentSearch(keyword, uuid);
        }

        return new ResponseEntity<>(searchProductIdListDto);
    }

//    @Operation(summary = "최근 검색어", description = "최근 검색어 목록 보기")
//    @GetMapping("/recent")
//    public ResponseEntity<?> recentSearchKeyword(@RequestHeader(value = "Authorization", required = false) String token) {
//
//    }

}
