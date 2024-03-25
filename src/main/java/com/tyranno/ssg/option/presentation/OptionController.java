package com.tyranno.ssg.option.presentation;


import com.tyranno.ssg.option.application.OptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor  // final로 선언된 필드를 초기화 하는 생성자를 만들어준다.
@RestController
@Tag(name = "상품 옵션", description = "옵션 API")
@RequestMapping("/api/v1/option")
public class OptionController {

         private final OptionService optionService;




//         @GetMapping("/find-options")
//         public List<Option> filter(
//                 @RequestParam(value = "category", required = false) String category,
//                 @RequestParam(value = "minPrice", required = false) Integer minPrice,
//                 @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
//                 @RequestParam(value = "price", required = false) Integer price,
//                 @RequestParam(value = "stock", required = false) Integer stock
//         ) {
//             log.info("category: {}, minPrice: {}, maxPrice: {}, price: {}, stock: {}", category, minPrice, maxPrice, price, stock);
//             return optionService.filter(category, minPrice, maxPrice, price, stock);
//         }
}
