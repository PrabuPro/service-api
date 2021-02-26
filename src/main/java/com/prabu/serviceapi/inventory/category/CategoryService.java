package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategoryList();
}
