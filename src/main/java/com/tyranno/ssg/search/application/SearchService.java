package com.tyranno.ssg.search.application;

import com.tyranno.ssg.search.dto.searchProductIdListDto;

public interface SearchService {
    searchProductIdListDto getProductIdList(Integer sortCriterion, Integer page, String searchKeyword,
                                            Integer maxPrice, Integer minPrice, Float minRate);
    void addRecentSearch(String searchKeyword, String uuid);
}
