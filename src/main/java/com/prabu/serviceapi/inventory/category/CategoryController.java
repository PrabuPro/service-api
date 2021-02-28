package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import com.prabu.serviceapi.inventory.category.model.CategoryListDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/grid")
    public ResponseEntity<Page<CategoryDTO>> getCategoryGrid(PaginationPage paginationPage){
        return new ResponseEntity<>(categoryService.getCategoryGrid(paginationPage), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.saveCategory(categoryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
