package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.inventory.category.Category;
import com.prabu.serviceapi.inventory.category.CategoryRepository;
import com.prabu.serviceapi.inventory.inventory.mapper.InventoryMapper;
import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class InventoryServiceImplTest {

    public static final long ID = 1L;
    public static final String PART_NAME = "part name";
    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    CategoryRepository categoryRepository;

    InventoryService inventoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventoryService = new InventoryServiceImpl(inventoryRepository, InventoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getInventoryList() {
        Inventory inventory1 = new Inventory();
        inventory1.setId(ID);
        inventory1.setPartName(PART_NAME);

        Inventory inventory2 = new Inventory();
        inventory2.setId(ID);
        inventory2.setPartName(PART_NAME);

        List<Inventory> inventoryList = Arrays.asList(inventory1, inventory2);

        when(inventoryRepository.findAll()).thenReturn(inventoryList);

        List<InventoryDTO> inventoryDTOList = inventoryService.getInventoryList();

        assertEquals(2, inventoryDTOList.size());

    }

    @Test
    void getInventoryGrid(){
        Inventory inventory1 = new Inventory();
        inventory1.setId(ID);
        inventory1.setPartName(PART_NAME);

        Inventory inventory2 = new Inventory();
        inventory2.setId(ID);
        inventory2.setPartName(PART_NAME);

        List<Inventory> inventoryList = Arrays.asList(inventory1, inventory2);

        Page<Inventory> inventoryPage = new PageImpl<>(inventoryList);

        when(inventoryRepository.findAll(any(Pageable.class))).thenReturn(inventoryPage);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        Page<InventoryDTO> inventoryDTOList = inventoryService.getInventoryGrid(paginationPage);

        assertEquals(2, inventoryDTOList.getTotalElements());
    }

    @Test
    void saveInventory(){
        InventoryCreateDTO requestBody = new InventoryCreateDTO();
        requestBody.setPartName(PART_NAME);
        requestBody.setCategoryId(1L);

        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setPartName(PART_NAME);

        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("category name");

        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        when(categoryRepository.findById(anyLong())).thenReturn(java.util.Optional.of(category));

        InventoryDTO inventoryDTO = inventoryService.saveInventory(requestBody);

        assertEquals(inventoryDTO.getId(), 1);
        assertEquals(inventoryDTO.getPartName(), PART_NAME);
    }

    @Test
    void updateInventory(){
        InventoryCreateDTO requestBody = new InventoryCreateDTO();
        requestBody.setPartName(PART_NAME);
        requestBody.setCategoryId(1L);

        Inventory inventory = new Inventory();
        inventory.setId(1L);
        inventory.setPartName(PART_NAME);

        Category category = new Category();
        category.setId(1L);
        category.setCategoryName("category name");

        when(inventoryRepository.save(any(Inventory.class))).thenReturn(inventory);
        when(categoryRepository.findById(anyLong())).thenReturn(java.util.Optional.of(category));

        InventoryDTO inventoryDTO = inventoryService.updateInventory(ID, requestBody);

        assertEquals(inventoryDTO.getId(), 1);
        assertEquals(inventoryDTO.getPartName(), PART_NAME);
    }

    @Test
    void deleteInventory(){
        inventoryService.deleteInventory(1L);

        verify(inventoryRepository, times(1)).deleteById(anyLong());
    }
}