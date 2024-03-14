package com.tyranno.ssg.category.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
@RestController
public class CategoryController {

    //    @GetMapping("/api/v1/category")
//    public String getExampleCategory() {
//        return "Hello World";
//    }
    @GetMapping("/api/v1/category")
    public List<ExampleCategory> getExampleCategory() {
        return Arrays.asList(
                new ExampleCategory(1, "스포츠/레저"),
                new ExampleCategory(2, "주방/세제"),
                new ExampleCategory(3, "문구"),
                new ExampleCategory(4, "전자기기"),
                new ExampleCategory(5, "식품")
        );
    }

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class ExampleCategory {
    private int category;
    private String categoryName;
}
