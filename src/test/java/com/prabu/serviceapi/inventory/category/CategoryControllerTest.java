package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
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

}