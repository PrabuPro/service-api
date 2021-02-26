package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.mapper.CategoryMapper;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    public static final long ID = 1L;
    public static final String CATEGORY_NAME = "categoryName";
    @Mock
    CategoryRepository categoryRepository;

    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getCategoryList() {
        Category category = new Category();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        Category category1 = new Category();
        category1.setId(ID);
        category1.setCategoryName(CATEGORY_NAME);

        List<Category> categories = Arrays.asList(category, category1);

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDTO> categoryDTOList = categoryService.getCategoryList();

        assertEquals(2, categoryDTOList.size());

    }
}