package com.tyranno.ssg.option.application;

import com.tyranno.ssg.option.infrastructure.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{
    private final OptionRepository optionRepository;

    @Override
    public List<String> findOptionAble(Long productId) {
        // 옵션 리스트 ex ["색상","사이즈"]

        return null;
    }
}
