package com.tyranno.ssg.option.dto;


import com.tyranno.ssg.option.domain.Color;
import com.tyranno.ssg.option.domain.Etc;
import com.tyranno.ssg.option.domain.Extra;
import com.tyranno.ssg.option.domain.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptionAbleListDto {
    private List<ColorStockDto> colorList;
    private List<SizeStockDto> sizeList;
    private List<EtcStockDto> etcList;
    private List<ExtraStockDto> extraList;


    public static OptionAbleListDto fromEntity(List<ColorStockDto> colorList, List<SizeStockDto> sizeList, List<EtcStockDto> etcList, List<ExtraStockDto> extraList) {
        return OptionAbleListDto.builder()
                .colorList(colorList)
                .sizeList(sizeList)
                .etcList(etcList)
                .extraList(extraList)
                .build();
    }
}


//{
//  "color": [
//    {
//      "id": 1,
//      "color": "빨강"
//    },
//    {
//      "id": 2,
//      "color": "파랑"
//    }
//  ],
//  "size": [
//    {q
//      "id": 1,
//      ""
//      "size": "240"
//    },
//    {
//      "id": 2,
//      "size": "250"
//    }
//  ],
//  "etc": [
//    {
//      "id": 1,
//      "additionalOption": "리미티드에디션"
//    }
//  ],
//  "extra": [
//    {
//      "id": 1,
//      "extraName": "신발끈추가",
//      "extraPrice": 100
//    }
//  ]
//}
