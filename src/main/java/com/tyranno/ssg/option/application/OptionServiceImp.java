package com.tyranno.ssg.option.application;

import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.awt.SystemColor.info;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{
    private final OptionRepository optionRepository;

    @Override
    public List<String> findOptionAble(Long productId) {
        // 옵션 리스트 ex ["색상","사이즈"]
        LinkedHashSet<String> optionAble = new LinkedHashSet<>(); // 요소를 추가된 순서대로 순회 가능하게 한다.
        List<?> options = optionRepository.findname(productId);
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
}
