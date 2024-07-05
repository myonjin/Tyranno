package com.tyranno.ssg.option.application;

import com.tyranno.ssg.global.GlobalException;
import com.tyranno.ssg.global.ResponseStatus;
import com.tyranno.ssg.option.domain.Option;
import com.tyranno.ssg.option.dto.*;
import com.tyranno.ssg.option.infrastructure.OptionRepository;
import com.tyranno.ssg.option.infrastructure.OptionRepositoryImpl;
import com.tyranno.ssg.product.domain.Discount;
import com.tyranno.ssg.product.infrastructure.DiscountRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OptionServiceImp implements OptionService {
    private final OptionRepository optionRepository;
    private final OptionRepositoryImpl optionRepositoryImp;
    private final DiscountRepository discountRepository;

    @Override

    public OptionNamesDto getOptionNames(Long optionId) {
        return optionRepository.findById(optionId)
                .map(OptionNamesDto::FromEntity)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));
    }

    @Override
    public List<String> getOptionAble(Long productId) {
        // 옵션 리스트 ex ["색상","사이즈"]
        LinkedHashSet<String> optionAble = new LinkedHashSet<>(); // 요소를 추가된 순서대로 순회 가능하게 한다.
        List<?> options = optionRepository.findName(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.DUPLICATE_ID));
//        options.forEach(option -> log.info(option.toString()));
        for (Object option : options) {
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
    public List<OptionAbleListDto> getOptionAbleList(Long productId) {

        List<Option> options = optionRepository.findAllByProductId(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_OPTION));
        Map<Long, ColorStockDto> colorStockMap = options.stream()
                .filter(option -> option.getColor() != null)
                .map(option -> new ColorStockDto(option.getColor().getId(), option.getColor().getColor(),
                        option.getStock()))
                .collect(Collectors.toMap(
                        ColorStockDto::getColorId,
                        dto -> dto,
                        (existingDto, newDto) -> new ColorStockDto(existingDto.getColorId(), existingDto.getColor(),
                                existingDto.getStock() + newDto.getStock())
                ));
        Map<Long, SizeStockDto> sizeStockMap = options.stream()
                .filter(option -> option.getSize() != null)
                .map(option -> new SizeStockDto(option.getSize().getId(), option.getSize().getSize(),
                        option.getStock()))
                .collect(Collectors.toMap(
                        SizeStockDto::getSizeId,
                        dto -> dto,
                        (existingDto, newDto) -> new SizeStockDto(existingDto.getSizeId(), existingDto.getSize(),
                                existingDto.getStock() + newDto.getStock())
                ));
        Map<Long, EtcStockDto> etcStockMap = options.stream()
                .filter(option -> option.getEtc() != null)
                .map(option -> new EtcStockDto(option.getEtc().getId(), option.getEtc().getAdditionalOption(),
                        option.getStock()))
                .collect(Collectors.toMap(
                        EtcStockDto::getEtcId,
                        dto -> dto,
                        (existingDto, newDto) -> new EtcStockDto(existingDto.getEtcId(),
                                existingDto.getAdditionalOption(), existingDto.getStock() + newDto.getStock())
                ));
        Map<Long, ExtraStockDto> extraStockMap = options.stream()
                .filter(option -> option.getExtra() != null)
                .map(option -> new ExtraStockDto(option.getExtra().getId(), option.getExtra().getExtraName(),
                        option.getStock(), option.getExtra().getExtraPrice()))
                .collect(Collectors.toMap(
                        ExtraStockDto::getExtraId,
                        dto -> dto,
                        (existingDto, newDto) -> new ExtraStockDto(existingDto.getExtraId(), existingDto.getExtraName(),
                                existingDto.getStock() + newDto.getStock(),
                                existingDto.getExtraPrice() + newDto.getExtraPrice())
                ));
//                log.info(options.toString());
//        List<Color> colors = options.stream()
//                .map(Option::getColor) // Option -> Color로 매핑
//                .filter(Objects::nonNull) // null이 아닌 Color 객체만 필터링
//                .distinct() // 중복 제거
//                .toList(); // List<Color>로 수집
//        log.info(colorStockMap.toString());
//        List<Size> sizes = options.stream()
//                .map(Option::getSize)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();

//        List<Extra> extras = options.stream()
//                .map(Option::getExtra)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();
//        List<Etc> etcs = options.stream()
//                .map(Option::getEtc)
//                .filter(Objects::nonNull)
//                .distinct()
//                .toList();

//        log.info(colors.toString());
//        return List.of(OptionAbleListDto.fromEntity(colors, sizes, etcs, extras));
        return List.of(OptionAbleListDto.fromEntity(new ArrayList<>(colorStockMap.values()),
                new ArrayList<>(sizeStockMap.values()), new ArrayList<>(etcStockMap.values()),
                new ArrayList<>(extraStockMap.values())));
    }


    @Override
    public List<OptionDto> getOptionProduct(Long productId, Long colorId, Long sizeId, Long extraId, Long etcId) {
        List<Option> optionProducts = optionRepositoryImp.getOptionProduct(productId, colorId, sizeId, extraId, etcId);
        //log.info(optionProducts.toString());
        if (optionProducts.isEmpty()) {
            throw new GlobalException(ResponseStatus.NO_SELECTED_OPTION);
        }
        int discount = discountRepository.findByProductId(productId)
                .orElseThrow(() -> new GlobalException(ResponseStatus.NO_EXIST_DISCOUNT))
                .getDiscount();


        return optionProducts.stream()
                .map(option -> OptionDto.fromEntity(option, discount))
                .toList();
    }

}
