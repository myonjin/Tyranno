package com.tyranno.ssg.option.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Color;
import com.tyranno.ssg.option.domain.Etc;
import com.tyranno.ssg.option.domain.Extra;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.domain.Size;
import com.tyranno.ssg.option.dto.ColorStockDto;
import com.tyranno.ssg.option.dto.OptionAbleListDto;
import com.tyranno.ssg.option.dto.OptionDto;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.option.infrastructure.OptionRepositoryImpl;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService {
    private final OptionRepository optionRepository;
    private final OptionRepositoryImpl optionRepositoryImp;

    @Override
    public List<String> findOptionAble(Long productId) {
        // 옵션 리스트 ex ["색상","사이즈"]
        LinkedHashSet<String> optionAble = new LinkedHashSet<>(); // 요소를 추가된 순서대로 순회 가능하게 한다.
        List<?> options = optionRepository.findName(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.DUPLICATE_ID));
//        options.forEach(option -> log.info(option.toString()));
        for (Object option : options) {
            Option opt = (Option) option;
            if (opt.getStock() == 0) {
                continue;
            }
            if (opt.getColor() != null) {
                optionAble.add("color");
            }
            if (opt.getSize() != null) {
                optionAble.add("size");
            }
            if (opt.getExtra() != null) {
                optionAble.add("extra");
            }
            if (opt.getEtc() != null) {
                optionAble.add("etc");
            }
        }

        return new ArrayList<>(optionAble);
    }

    @Override
    public List<OptionAbleListDto> findOptionAbleList(Long productId) {

        List<Option> options = optionRepository.findAllByProductId(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.DUPLICATE_ID));
//        log.info(options.toString());
        List<Color> colors = options.stream()
                .map(Option::getColor) // Option -> Color로 매핑
                .filter(Objects::nonNull) // null이 아닌 Color 객체만 필터링
                .distinct() // 중복 제거
                .toList(); // List<Color>로 수집
        Map<Long, ColorStockDto> colorStockMap = options.stream()
                .filter(option -> option.getColor() != null)
                .map(option -> new ColorStockDto(option.getColor().getId(), option.getColor().getColor(), option.getStock()))
                .collect(Collectors.toMap(
                        ColorStockDto::getColorId,
                        dto -> dto,
                        (existingDto, newDto) -> new ColorStockDto(existingDto.getColorId(), existingDto.getColor(), existingDto.getStock() + newDto.getStock())
                ));
        log.info(colorStockMap.toString());
        List<Size> sizes = options.stream()
                .map(Option::getSize)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        List<Extra> extras = options.stream()
                .map(Option::getExtra)
                .filter(Objects::nonNull)
                .distinct()
                .toList();
        List<Etc> etcs = options.stream()
                .map(Option::getEtc)
                .filter(Objects::nonNull)
                .distinct()
                .toList();


//        log.info(colors.toString());
        return List.of(OptionAbleListDto.fromEntity(colors, sizes, etcs, extras));
    }

    @Override
    public List<OptionDto> getOptionProduct(Long productId, Long colorId, Long sizeId, Long extraId, Long etcId) {
        List<Option> optionProducts = optionRepositoryImp.getOptionProduct(productId, colorId, sizeId, extraId, etcId);
//        log.info(optionProducts.toString());
        return optionProducts.stream()
                .map(OptionDto::fromEntity)
                .toList();
    }

}
