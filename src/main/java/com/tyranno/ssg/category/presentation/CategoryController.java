package com.tyranno.ssg.category.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @GetMapping("/api/v1/category")
    public String getCategory() {
        return "Hello World";
    }
}