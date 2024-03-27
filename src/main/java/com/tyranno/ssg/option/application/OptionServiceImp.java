package com.tyranno.ssg.option.application;

import com.tyranno.ssg.option.infrastucture.OptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService{
    private final OptionRepository optionRepository;

}
