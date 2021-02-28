package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategoryList();

    Page<CategoryDTO> getCategoryGrid(PaginationPage paginationPage);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    CategoryDTO saveCategory(CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
