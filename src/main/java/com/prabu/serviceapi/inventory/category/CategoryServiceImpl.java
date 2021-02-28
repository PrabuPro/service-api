package com.prabu.serviceapi.inventory.category;

import com.prabu.serviceapi.inventory.category.mapper.CategoryMapper;
import com.prabu.serviceapi.inventory.category.model.CategoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getCategoryList() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CategoryDTO> getCategoryGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(), paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);
        List<CategoryDTO> categoryDTOList = categoryRepository.findAll(pageable)
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(categoryDTOList);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        category.setId(id);

        return saveCategoryToRepository(category);
    }

    @Transactional
    private CategoryDTO saveCategoryToRepository(Category category){
        return categoryMapper.categoryToCategoryDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        return saveCategoryToRepository(categoryMapper.categoryDTOToCategory(categoryDTO)) ;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
