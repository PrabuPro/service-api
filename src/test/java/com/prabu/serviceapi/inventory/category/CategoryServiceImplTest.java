package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.mapper.CategoryMapper;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    void getCategoryGrid(){
        Category category = new Category();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        Category category1 = new Category();
        category1.setId(ID);
        category1.setCategoryName(CATEGORY_NAME);

        List<Category> categories = Arrays.asList(category, category1);

        Page<Category> categoryPage = new PageImpl<>(categories);

        when(categoryRepository.findAll((Pageable) any())).thenReturn(categoryPage);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        Page<CategoryDTO> pageData = categoryService.getCategoryGrid(paginationPage);

        assertEquals(2, pageData.getTotalElements());

    }

    @Test
    void saveCategory(){
        Category category = new Category();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(CATEGORY_NAME);

        when(categoryRepository.save(any())).thenReturn(category);

        CategoryDTO categoryDTOSave = categoryService.saveCategory(categoryDTO);
        assertEquals(categoryDTOSave.getId(), ID);
        assertEquals(categoryDTOSave.getCategoryName(), CATEGORY_NAME);

    }

    @Test
    void updateCategory(){
        Category category = new Category();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryName(CATEGORY_NAME);

        when(categoryRepository.save(any())).thenReturn(category);

        CategoryDTO categoryDTOSave = categoryService.updateCategory(ID, categoryDTO);
        assertEquals(categoryDTOSave.getId(), ID);
        assertEquals(categoryDTOSave.getCategoryName(), CATEGORY_NAME);

    }

    @Test
    void deleteCategory(){
        categoryService.deleteCategory(1L);

        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
}