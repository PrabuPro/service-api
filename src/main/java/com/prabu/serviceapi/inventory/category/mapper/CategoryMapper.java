package com.prabu.serviceapi.inventory.category.mapper;

import com.prabu.serviceapi.inventory.category.Category;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);
    CategoryDTO categoryToCategoryDTO(Category category);

}
