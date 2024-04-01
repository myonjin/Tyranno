package com.tyranno.ssg.option.application;

import com.tyranno.ssg.option.dto.OptionAbleListDto;
import com.tyranno.ssg.option.dto.OptionDto;
import com.tyranno.ssg.option.dto.OptionNamesDto;

import java.util.List;

public interface OptionService {

    List<String> getOptionAble(Long productId);
    OptionNamesDto getOptionNames(Long optionId);
    List<OptionAbleListDto> getOptionAbleList(Long productId);
    List<OptionDto> getOptionProduct(Long productId, Long colorId, Long sizeId, Long extraId, Long etcId);
}
