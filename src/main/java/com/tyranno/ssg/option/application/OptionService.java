package com.tyranno.ssg.option.application;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.dto.OptionAbleListDto;

import java.util.List;

public interface OptionService {

    List<String> findOptionAble(Long productId);

    List<OptionAbleListDto> findOptionAbleList(Long productId);
}
