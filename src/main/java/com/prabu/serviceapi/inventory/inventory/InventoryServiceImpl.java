package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.exception.ResourceNotFoundException;
import com.prabu.serviceapi.inventory.category.Category;
import com.prabu.serviceapi.inventory.category.CategoryRepository;
import com.prabu.serviceapi.inventory.inventory.mapper.InventoryMapper;
import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
import com.prabu.serviceapi.pagination.PaginationPage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final CategoryRepository categoryRepository;

    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, CategoryRepository categoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<InventoryDTO> getInventoryList() {
        return inventoryRepository.findAll()
                .stream()
                .map(inventoryMapper::InventoryToInventoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<InventoryDTO> getInventoryGrid(PaginationPage paginationPage) {
        Sort sort = Sort.by(paginationPage.getSortDirection(), paginationPage.getSortBy());
        Pageable pageable = PageRequest.of(
                paginationPage.getPageNumber(),
                paginationPage.getPageSize(),
                sort);

        List<InventoryDTO> inventoryList = inventoryRepository.findAll(pageable)
                .stream()
                .map(inventoryMapper::InventoryToInventoryDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(inventoryList);
    }

    @Override
    public InventoryDTO saveInventory(InventoryCreateDTO inventoryCreateDTO) {
        Category category = categoryRepository.findById(inventoryCreateDTO.getCategoryId()).orElseThrow(ResourceNotFoundException::new);
        Inventory inventory = inventoryMapper.InventoryDTOToInventory(inventoryCreateDTO,category);

        return saveInventoryToRepository(inventory);
    }

    private InventoryDTO saveInventoryToRepository(Inventory inventory){
        Inventory savedInventory = inventoryRepository.save(inventory);
        return inventoryMapper.InventoryToInventoryDTO(savedInventory);
    }

    @Override
    public InventoryDTO updateInventory(Long id, InventoryCreateDTO inventoryCreateDTO) {
        Category category = categoryRepository.findById(inventoryCreateDTO.getCategoryId()).orElseThrow(ResourceNotFoundException::new);
        Inventory inventory = inventoryMapper.InventoryDTOToInventory(inventoryCreateDTO,category);
        inventory.setId(id);
        return saveInventoryToRepository(inventory);
    }

    @Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
