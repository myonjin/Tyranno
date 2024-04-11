package com.tyranno.ssg.search.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.search.domain.Search;
import com.tyranno.ssg.search.dto.searchProductIdListDto;
import com.tyranno.ssg.search.infrastructure.SearchRepository;
import com.tyranno.ssg.search.infrastructure.SearchRepositoryImp;
import com.tyranno.ssg.users.domain.Users;
import com.tyranno.ssg.users.infrastructure.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class SearchServiceImp implements SearchService{
    private final SearchRepository searchRepository;
    private final SearchRepositoryImp searchRepositoryImp;
    private final UsersRepository usersRepository;
    public searchProductIdListDto getProductIdList(Integer sortCriterion, Integer page, String searchKeyword,
                                                   Integer maxPrice, Integer minPrice, Float minRate) {
        final int PAGE_SIZE = 10;
        List<Long> productIds;
        productIds = searchRepositoryImp.searchProductIdsByKeywordAndFilter(searchKeyword, sortCriterion, page,
                maxPrice, minPrice, minRate);
        searchProductIdListDto searchProductIdListDto = new searchProductIdListDto();

        List<Map<String, Object>> productIdList = new ArrayList<>();
        int startIndex = (page - 1) * PAGE_SIZE; // 페이지 번호가 1부터 시작
        for (int i = 0; i < productIds.size(); i++) {
            Long productId = productIds.get(i);
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("productId", productId);
            productMap.put("idx", startIndex + i + 1); // 인덱스 1부터 시작
            productIdList.add(productMap);
        }
        searchProductIdListDto.setProductIds(productIdList);

        return searchProductIdListDto;
    }
    public void addRecentSearch(String searchKeyword, String uuid) {
        Users users = usersRepository.findByUuid(uuid).orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_USERS));
        Search search = Search.builder()
                .searchWord(searchKeyword)
                .users(users)
                .build();
        searchRepository.save(search);
    }


}
