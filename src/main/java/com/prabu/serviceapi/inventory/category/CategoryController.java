package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.model.CategoryListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CategoryController.API_V_1_CATEGORY)
public class CategoryController {
    public static final String API_V_1_CATEGORY = "/api/v1/category";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<CategoryListDTO> getCategoryList(){
        return new ResponseEntity<CategoryListDTO>(new CategoryListDTO(categoryService.getCategoryList()), HttpStatus.OK);

    }
}
