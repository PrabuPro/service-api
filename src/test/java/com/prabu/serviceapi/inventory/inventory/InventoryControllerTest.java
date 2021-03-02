package com.prabu.serviceapi.inventory.inventory;

import com.prabu.serviceapi.exception.GlobalExceptionHandler;
import com.prabu.serviceapi.inventory.category.CategoryController;
import com.prabu.serviceapi.inventory.inventory.model.InventoryCreateDTO;
import com.prabu.serviceapi.inventory.inventory.model.InventoryDTO;
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

import java.math.BigDecimal;
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

class InventoryControllerTest {

    public static final long ID = 1L;
    public static final String PART_NAME = "part name";

    @Mock
    InventoryService inventoryService;

    @InjectMocks
    InventoryController inventoryController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(inventoryController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    void getInventoryList() throws Exception {
        InventoryDTO inventory1 = new InventoryDTO();
        inventory1.setId(ID);
        inventory1.setPartName(PART_NAME);

        InventoryDTO inventory2 = new InventoryDTO();
        inventory2.setId(ID);
        inventory2.setPartName(PART_NAME);

        List<InventoryDTO> inventoryDTOList = Arrays.asList(inventory1, inventory2);

        when(inventoryService.getInventoryList()).thenReturn(inventoryDTOList);

        mockMvc.perform(get(InventoryController.API_V_1_INVENTORY + "/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inventories", hasSize(2)));

    }

    @Test
    void getInventoryGrid() throws Exception {
        InventoryDTO inventory1 = new InventoryDTO();
        inventory1.setId(ID);
        inventory1.setPartName(PART_NAME);

        InventoryDTO inventory2 = new InventoryDTO();
        inventory2.setId(ID);
        inventory2.setPartName(PART_NAME);

        List<InventoryDTO> inventoryDTOList = Arrays.asList(inventory1, inventory2);

        Page<InventoryDTO> inventoryPage = new PageImpl<>(inventoryDTOList);

        PaginationPage paginationPage = new PaginationPage();
        paginationPage.setPageNumber(0);
        paginationPage.setPageSize(2);

        when(inventoryService.getInventoryGrid(ArgumentMatchers.any(PaginationPage.class))).thenReturn(inventoryPage);

        mockMvc.perform(get(InventoryController.API_V_1_INVENTORY + "/grid")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void saveInventory() throws Exception {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(1L);
        inventoryDTO.setPartName(PART_NAME);

        InventoryCreateDTO inventoryCreateDTO = new InventoryCreateDTO();
        inventoryCreateDTO.setPartName(PART_NAME);
        inventoryCreateDTO.setCategoryId(1L);
        inventoryCreateDTO.setPartNumber("number");
        inventoryCreateDTO.setPurchasedPrice(BigDecimal.valueOf(100.10));
        inventoryCreateDTO.setSalePrice(BigDecimal.valueOf(150.10));

        when(inventoryService.saveInventory(ArgumentMatchers.any(InventoryCreateDTO.class))).thenReturn(inventoryDTO);

        mockMvc.perform(post(InventoryController.API_V_1_INVENTORY )
                .content(asJsonString(inventoryCreateDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.partName", equalTo(PART_NAME)));

    }

    @Test
    void updateInventory() throws Exception {
        InventoryDTO inventoryDTO = new InventoryDTO();
        inventoryDTO.setId(1L);
        inventoryDTO.setPartName(PART_NAME);

        InventoryCreateDTO inventoryCreateDTO = new InventoryCreateDTO();
        inventoryCreateDTO.setPartName(PART_NAME);
        inventoryCreateDTO.setCategoryId(1L);
        inventoryCreateDTO.setPartNumber("number");
        inventoryCreateDTO.setPurchasedPrice(BigDecimal.valueOf(100.10));
        inventoryCreateDTO.setSalePrice(BigDecimal.valueOf(150.10));

        when(inventoryService.updateInventory(anyLong(),ArgumentMatchers.any(InventoryCreateDTO.class))).thenReturn(inventoryDTO);

        mockMvc.perform(put(InventoryController.API_V_1_INVENTORY + "/1" )
                .content(asJsonString(inventoryCreateDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.partName", equalTo(PART_NAME)));

    }

    @Test
    void deleteInventory() throws Exception {
        mockMvc.perform(delete(InventoryController.API_V_1_INVENTORY + "/1" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}