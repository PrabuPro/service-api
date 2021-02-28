package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.prabu.serviceapi.customer.AbstractRestController.asJsonString;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest {

    public static final long ID = 1L;
    public static final String CATEGORY_NAME = "category Name";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getCategoryList() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setCategoryName(CATEGORY_NAME);

        CategoryDTO categoryDTO1 = new CategoryDTO();
        categoryDTO1.setId(ID);
        categoryDTO1.setCategoryName(CATEGORY_NAME);

        List<CategoryDTO> categoryDTOList = Arrays.asList(categoryDTO, categoryDTO1);

        when(categoryService.getCategoryList()).thenReturn(categoryDTOList);

        mockMvc.perform(get(CategoryController.API_V_1_CATEGORY + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));

    }

    @Test
    void getCategoryGrid() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryDTO category1 = new CategoryDTO();
        category1.setId(ID);
        category1.setCategoryName(CATEGORY_NAME);

        List<CategoryDTO> categories = Arrays.asList(category, category1);

        Page<CategoryDTO> categoryPage = new PageImpl<>(categories);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        when(categoryService.getCategoryGrid(ArgumentMatchers.any())).thenReturn(categoryPage);

        mockMvc.perform(get(CategoryController.API_V_1_CATEGORY + "/grid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.categories", hasSize(2)));
    }

    @Test
    void categorySave() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryDTO categoryRequest = new CategoryDTO();
        category.setCategoryName(CATEGORY_NAME);

        when(categoryService.saveCategory(ArgumentMatchers.any())).thenReturn(category);

        mockMvc.perform(post(CategoryController.API_V_1_CATEGORY)
                .content(asJsonString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.categoryName", equalTo(CATEGORY_NAME)));
    }

    @Test
    void categoryUpdate() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(ID);
        category.setCategoryName(CATEGORY_NAME);

        CategoryDTO categoryRequest = new CategoryDTO();
        category.setCategoryName(CATEGORY_NAME);

        when(categoryService.updateCategory(anyLong(), ArgumentMatchers.any(CategoryDTO.class))).thenReturn(category);

        mockMvc.perform(put(CategoryController.API_V_1_CATEGORY + "/1")
                .content(asJsonString(categoryRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.categoryName", equalTo(CATEGORY_NAME)));
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete(CategoryController.API_V_1_CATEGORY + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}