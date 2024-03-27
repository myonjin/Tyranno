package com.tyranno.ssg.option.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Color;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.dto.OptionAbleListDto;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.awt.SystemColor.info;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{
    private final OptionRepository optionRepository;

    @Override
    public List<String> findOptionAble(Long productId) {
        // 옵션 리스트 ex ["색상","사이즈"]
        LinkedHashSet<String> optionAble = new LinkedHashSet<>(); // 요소를 추가된 순서대로 순회 가능하게 한다.
        List<?> options = optionRepository.findName(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.DUPLICATE_ID));
//        options.forEach(option -> log.info(option.toString()));
        for (Object option : options){
            Option opt = (Option) option;
            if (opt.getStock() == 0) continue;
            if (opt.getColor() != null) optionAble.add("color");
            if (opt.getSize() != null) optionAble.add("size");
            if (opt.getExtra() != null) optionAble.add("extra");
            if (opt.getEtc() != null) optionAble.add("etc");
        }

        return new ArrayList<>(optionAble);
    }

    @Override
    public List<OptionAbleListDto> findOptionAbleList(Long productId) {

        List<Option> options = optionRepository.findAllByProductId(productId)
                .orElseThrow(()-> new GlobalException(ResponseStatus.DUPLICATE_ID));
        List<Color> colors = options.stream()
                .map(Option::getColor) // Option -> Color로 매핑
                .filter(Objects::nonNull) // null이 아닌 Color 객체만 필터링
                .distinct() // 중복 제거
                .toList(); // List<Color>로 수집
        log.info(colors.toString());





        return null;
    }
}
